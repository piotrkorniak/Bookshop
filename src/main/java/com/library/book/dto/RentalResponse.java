package com.library.book.dto;

import com.library.book.entity.Rental;

import java.time.Instant;
import java.util.UUID;

public class RentalResponse {
    public UUID id;
    public String status;
    public Instant startDate;
    public Instant endDate;
    public GetBookDto book;
    public RenteeResponse rentee;

    public RentalResponse(Rental rental) {
        id = rental.getId();
        status = rental.getStatus().toString();
        startDate = rental.getStartDate().toInstant();
        endDate = rental.getEndDate() == null ? null : rental.getEndDate().toInstant();
        book = new GetBookDto(rental.getBook());
        rentee = new RenteeResponse(rental.getUser());
    }
}
