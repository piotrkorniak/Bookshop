package com.library.book.service.interfaces;

import com.library.auth.entity.User;
import com.library.book.dto.RentalResponse;

import java.util.List;
import java.util.UUID;

public interface IUserRentalService {
    List<RentalResponse> GetUserRentals(User user);

    RentalResponse GetUserRental(User user, UUID id);

    void CloseUserRental(User user, UUID id);

    void CreateUserRental(User user, UUID bookId);
}
