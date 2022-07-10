package com.library.book.entity;

import com.library.book.dto.AddBookDto;
import com.library.book.enums.BookStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private UUID Id;
    @NotNull
    private String Title;
    @NotNull
    private String Author;
    @NotNull
    private String Description;
    @NotNull
    private String ImageUrl;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Rental> rentals;

    public Collection<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(Collection<Rental> rentals) {
        this.rentals = rentals;
    }

    public Book() {
    }

    public Book(AddBookDto addBookDto) {
        Title = addBookDto.title;
        Author = addBookDto.author;
        Description = addBookDto.description;
        ImageUrl = addBookDto.imageUrl;
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

    public BookStatus getBookStatus() {
        return this.isBookRented() ? BookStatus.Unavailable : BookStatus.Available;
    }

    public boolean isBookRented() {
        return !(rentals.stream().allMatch(x -> x.getEndDate() != null));
    }

    public boolean wasBookRented() {
        return rentals.size() != 0;
    }

}
