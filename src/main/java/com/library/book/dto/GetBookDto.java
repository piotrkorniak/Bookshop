package com.library.book.dto;

import com.library.book.entity.Book;
import com.library.book.enums.BookStatus;

import java.util.UUID;

public class GetBookDto {
    public UUID id;
    public String title;
    public String author;
    public String description;
    public String imageUrl;

    public BookStatus status;

    public GetBookDto(Book book) {
        id = book.getId();
        title = book.getTitle();
        author = book.getAuthor();
        description = book.getDescription();
        imageUrl = book.getImageUrl();
        status = book.getBookStatus();
    }
}
