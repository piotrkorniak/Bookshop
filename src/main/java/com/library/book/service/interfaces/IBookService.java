package com.library.book.service.interfaces;

import com.library.book.dto.AddBookDto;
import com.library.book.dto.GetBookDto;

import java.util.List;
import java.util.UUID;

public interface IBookService {
    void AddBook(AddBookDto addBookDto);

    GetBookDto GetBook(UUID id);

    List<GetBookDto> GetBooks();

    void DeleteBook(UUID id);
}
