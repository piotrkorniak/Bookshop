package com.library.card.entity;

import com.library.book.entity.Book;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

@Entity
public class Card {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany
    private List<CardItem> items;

    public Card() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}