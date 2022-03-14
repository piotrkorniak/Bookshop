package com.library.book.service;

import com.library.book.dao.BookDao;
import com.library.book.dto.AddBookDto;
import com.library.book.dto.GetBookDto;
import com.library.book.entity.Book;
import com.library.book.service.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "roleService")
public class BookService implements IBookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public void AddBook(AddBookDto addBookDto) {
        var book = new Book(addBookDto);
        bookDao.save(book);
    }

    @Override
    public GetBookDto GetBook(long id) {
        var book = bookDao.getById(id);
        return new GetBookDto(book);
    }

    @Override
    public List<GetBookDto> GetBooks() {
        var books = bookDao.findAll();
        return books.stream().map(o -> new GetBookDto(o)).toList();
    }

    @Override
    public void DeleteBook(long id) {
        bookDao.deleteById(id);
    }
}
