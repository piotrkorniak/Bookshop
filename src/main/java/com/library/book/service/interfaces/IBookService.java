package com.library.book.service.interfaces;

import com.library.book.dto.AddBookDto;
import com.library.book.dto.GetBookDto;

import java.util.List;

public interface IBookService {
    public void AddBook(AddBookDto addBookDto);

    public GetBookDto GetBook(long id);

    public List<GetBookDto> GetBooks();

    public void DeleteBook(long id);
}
