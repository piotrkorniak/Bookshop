package com.library.book.entity;

import com.library.book.dto.AddBookDto;
import com.library.book.enums.BookStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID Id;
    @Column(length = 100)
    @NotNull
    private String Title;
    @Column(length = 100)
    @NotNull
    private String Author;
    @Column(length = 1000)
    @NotNull
    private String Description;
    @Column(length = 1000)
    @NotNull
    private String ImageUrl;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
