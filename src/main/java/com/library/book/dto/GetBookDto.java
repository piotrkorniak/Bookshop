package com.library.book.dto;

import com.library.book.entity.Book;

import java.util.UUID;

public class GetBookDto {
    private UUID Id;
    private String Title;
    private String Author;
    private String Description;
    private String ImageUrl;

    public GetBookDto(Book book) {
        Id = book.getId();
        Title = book.getTitle();
        Author = book.getAuthor();
        Description = book.getDescription();
        ImageUrl = book.getImageUrl();
    }


    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
