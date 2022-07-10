package com.library.book.controller;

import com.library.book.dto.GetBookDto;
import com.library.book.service.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<GetBookDto>> getBooks(@RequestParam(value = "", required = false) String globalSearch, @RequestParam(required = false) boolean isAvailable) {
        var searchValue = globalSearch == null ? "" : globalSearch;
        return ResponseEntity.ok(bookService.GetBooks(searchValue, isAvailable));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<GetBookDto> getBook(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bookService.GetBook(id));
    }
}
