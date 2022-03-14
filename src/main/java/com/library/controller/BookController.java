package com.library.controller;

import com.library.entity.Book;
import com.library.entity.request.AddBookRequest;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addBook(AddBookRequest addBookRequest) {
        Book book = new Book();
        book.setDescription(addBookRequest.getDescription());
        book.setAuthor(addBookRequest.getAuthor());
        book.setTitle(addBookRequest.getTitle());
        book.setImageUrl(addBookRequest.getImageUrl());
        bookRepository.save(book);
    }
}
