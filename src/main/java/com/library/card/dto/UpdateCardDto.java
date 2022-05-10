package com.library.card.dto;

import com.library.book.dto.GetBookDto;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.UUID;

public class UpdateCardDto {
    private List<Pair<Integer, UUID>> cardItems;

    public List<Pair<Integer, UUID>> getCardItems() {
        return cardItems;
    }

    public void setCardItems(List<Pair<Integer, UUID>> cardItems) {
        this.cardItems = cardItems;
    }
}
