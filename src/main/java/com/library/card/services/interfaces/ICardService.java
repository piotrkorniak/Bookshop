package com.library.card.services.interfaces;

import com.library.card.dto.GetCardDto;
import com.library.card.dto.UpdateCardDto;

import java.util.UUID;

public interface ICardService {
    public GetCardDto updateCard(UpdateCardDto updateCardDto);

    public void removeCard(UUID cardId);

    public GetCardDto getCard(UUID cardId);
}
