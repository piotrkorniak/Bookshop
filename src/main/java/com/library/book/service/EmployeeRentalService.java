package com.library.book.service;

import com.library.book.dao.BookDao;
import com.library.book.dto.AddBookDto;
import com.library.book.entity.Book;
import com.library.book.service.interfaces.IEmployeeRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class EmployeeRentalService implements IEmployeeRentalService {
    @Autowired
    private BookDao bookDao;

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
}
