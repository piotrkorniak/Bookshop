package com.library.book.service.interfaces;

import com.library.auth.entity.User;
import com.library.book.dto.AddBookDto;

import java.util.UUID;

public interface IEmployeeRentalService {
    void AddBook(AddBookDto bookDto);

    void deleteBook(UUID id);
}
