package com.library.book.controller;

import com.library.book.dto.AddBookDto;
import com.library.book.dto.GetBookDto;
import com.library.book.service.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;


    @RequestMapping(method = RequestMethod.GET)
    public List<GetBookDto> getBooks() {
        return bookService.GetBooks();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GetBookDto getBook(@PathVariable("id") Long id) {
        return bookService.GetBook(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        bookService.DeleteBook(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public void addBook(AddBookDto addBookDto) {
        bookService.AddBook(addBookDto);
    }
}
