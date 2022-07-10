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

    @Override
    public List<RentalResponse> GetUserRentals(User user) {
        var rentals = rentalDao.findAll().stream().filter(x -> x.getUser().getId().equals(user.getId()));
        return rentals.map(x -> new RentalResponse(x)).toList();
    }

    @Override
    public RentalResponse GetUserRental(User user, UUID id) {
        var rental = rentalDao.findAll().stream().filter(x -> x.getId().equals(id) && x.getUser().getId().equals(user.getId())).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Common.NotFound"));
        return new RentalResponse(rental);
    }

    @Override
    @Transactional
    public void CloseUserRental(User user, UUID id) {
        var rental = rentalDao.findAll().stream().filter(x -> x.getId().equals(id) && x.getUser().getId().equals(user.getId())).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Common.NotFound"));

        if (!rental.isPending()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Common.RentalIsNotPending");
        }

        rental.CloseRental();
    }

    @Override
    @Transactional
    public void CreateUserRental(User user, UUID bookId) {
        Book book = bookDao.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Rental.BookIsNull"));


        if (book.isBookRented()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Rental.BookInUse");
        }

        var rental = new Rental();
        rental.setBook(book);
        rental.setUser(user);
        book.getRentals().add(rental);
    }
}
