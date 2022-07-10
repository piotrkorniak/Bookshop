package com.library.book.service;

import com.library.auth.dao.UserDao;
import com.library.auth.entity.User;
import com.library.book.dao.BookDao;
import com.library.book.dao.RentalDao;
import com.library.book.dto.RentalResponse;
import com.library.book.entity.Book;
import com.library.book.entity.Rental;
import com.library.book.service.interfaces.IUserRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UserRentalService implements IUserRentalService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private RentalDao rentalDao;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public List<RentalResponse> GetUserRentals(User user) {
        var rentals = user.getRentals();
        return rentals.stream().map(x -> new RentalResponse(x)).toList();
    }

    @Override
    @Transactional
    public RentalResponse GetUserRental(User user, UUID id) {
        Rental rental = user.getRentals().stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Common.NotFound"));
        return new RentalResponse(rental);
    }

    @Override
    @Transactional
    public void CloseUserRental(User user, UUID id) {
        Rental rental = user.getRentals().stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Common.NotFound"));


        if (!rental.isPending()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Common.RentalIsNotPending");
        }

        rental.CloseRental();
        rentalDao.save(rental);
    }

    @Override
    @Transactional
    public void CreateUserRental(User user, UUID bookId) {
        Book book = bookDao.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Rental.BookIsNull"));


        if (book.isBookRented()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Rental.BookInUse");
        }

        var rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        user.getRentals().add(rental);
        book.getRentals().add(rental);
        rentalDao.save(rental);
    }
}
