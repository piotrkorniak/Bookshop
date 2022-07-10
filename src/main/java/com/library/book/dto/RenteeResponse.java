package com.library.book.dto;

import com.library.auth.entity.User;

import java.util.UUID;

public class RenteeResponse {
    public UUID id;
    public String email;
    public String firstName;
    public String lastName;

    public RenteeResponse(User user) {
        id = user.getId();
        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();
    }
}
