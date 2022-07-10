package com.library.book.service;

import com.library.book.dao.BookDao;
import com.library.book.dao.RentalDao;
import com.library.book.dto.AddBookDto;
import com.library.book.dto.RentalResponse;
import com.library.book.entity.Book;
import com.library.book.service.interfaces.IEmployeeRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeRentalService implements IEmployeeRentalService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private RentalDao rentalDao;

    @Override
    @Transactional
    public void AddBook(AddBookDto bookDto) {
        var newBook = new Book(bookDto);
        bookDao.save(newBook);
    }

    @Override
    @Transactional
    public void deleteBook(UUID id) {
        Book book = bookDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Rental.BookIsNull"));

        if (book.wasBookRented()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Book.BookWasUse");
        }

        bookDao.delete(book);
    }

    @Override
    public List<RentalResponse> GetEmployeeRentals() {
        var rentals = rentalDao.findAll();
        return rentals.stream().map(x -> new RentalResponse(x)).toList();
    }

    @Override
    public RentalResponse GetEmployeeRental(UUID id) {
        var rental = rentalDao.findAll().stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Common.NotFound"));
        return new RentalResponse(rental);
    }

    @Override
    @Transactional
    public void closeEmployeeRental(UUID id) {
        var rental = rentalDao.findAll().stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Common.NotFound"));

        if (rental.isCanceled()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Common.RentalIsCanceled");
        }

        if (rental.isCompleted()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Common.RentalIsCompleted");
        }

        rental.CloseRental();
    }

    @Override
    @Transactional
    public void activeEmployeeRental(UUID id) {
        var rental = rentalDao.findAll().stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Common.NotFound"));

        if (!rental.isPending()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Common.RentalIsNotPending");
        }

        rental.activateRental();
    }
}
