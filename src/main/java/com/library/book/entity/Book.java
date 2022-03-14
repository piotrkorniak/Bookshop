package com.library.book.entity;

import com.library.book.dto.AddBookDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private long Id;
    private String Title;
    private String Author;
    private String Description;
    private String ImageUrl;

    public Book(AddBookDto addBookDto) {
        Title = addBookDto.getTitle();
        Author = addBookDto.getAuthor();
        Description = addBookDto.getDescription();
        ImageUrl = addBookDto.getImageUrl();
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
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
