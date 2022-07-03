package com.library.card.entity;

import com.library.book.entity.Book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class CardItem {
    @Id
    @GeneratedValue
    private UUID id;

    private int amount;

    @ManyToOne
    private Book value;

    public CardItem(int amount, Book value) {
        this.amount = amount;
        this.value = value;
    }

    public CardItem() {

    }

    public int getAmount() {
        return amount;
    }

    public Book getValue() {
        return value;
    }
}
