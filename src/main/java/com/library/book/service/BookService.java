package com.library.book.service;

import com.library.book.dao.BookDao;
import com.library.book.dto.GetBookDto;
import com.library.book.service.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookDao bookDao;


    @Override
    public GetBookDto GetBook(UUID id) {
        var book = bookDao.getById(id);
        return new GetBookDto(book);
    }

    @Override
    public List<GetBookDto> GetBooks(String searchValue, boolean isAvailable) {
        var booksQuery = bookDao.findAll().stream().filter(x -> x.getAuthor().contains(searchValue) || x.getTitle().contains(searchValue));

        if (isAvailable) {
            booksQuery = booksQuery.filter(x -> !x.isBookRented());
        }

        var books = booksQuery.toList();

        return books.stream().map(o -> new GetBookDto(o)).toList();
    }
}
