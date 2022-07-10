package com.library.book.service.interfaces;

import com.library.book.dto.AddBookDto;
import com.library.book.dto.RentalResponse;

import java.util.List;
import java.util.UUID;

public interface IEmployeeRentalService {
    void AddBook(AddBookDto bookDto);

    void deleteBook(UUID id);

    List<RentalResponse> GetEmployeeRentals();

    RentalResponse GetEmployeeRental(UUID id);

    void closeEmployeeRental(UUID id);

    void activeEmployeeRental(UUID id);
}
