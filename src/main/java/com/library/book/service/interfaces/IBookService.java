package com.library.book.service.interfaces;

import com.library.book.dto.AddBookDto;
import com.library.book.dto.GetBookDto;

import java.util.List;
import java.util.UUID;

public interface IBookService {
    public void AddBook(AddBookDto addBookDto);

    public GetBookDto GetBook(UUID id);

    public List<GetBookDto> GetBooks();

    public void DeleteBook(UUID id);
}
