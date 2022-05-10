package com.library.card.dto;

import com.library.book.dto.GetBookDto;
import com.library.card.entity.Card;
import org.springframework.data.util.Pair;

import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

public class GetCardDto {
    private UUID id;
    private Dictionary<GetBookDto, Integer> cardItems;

    public GetCardDto(Card card) {
        this.id = card.getId();
        //card.getItems().forEach(x-> cardItems.put(x.getValue(), x.getAmount()));
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
//
//    public List<Pair<Integer, GetBookDto>> getCardItems() {
//        return cardItems;
//    }
//
//    public void setCardItems(List<Pair<Integer, GetBookDto>> cardItems) {
//        this.cardItems = cardItems;
//    }
}
