package com.library.card.services.interfaces;

import com.library.card.dto.GetCardDto;
import com.library.card.dto.UpdateCardDto;

import java.util.UUID;

public interface ICardService {
    GetCardDto updateCard(UpdateCardDto updateCardDto);

    void removeCard(UUID cardId);

    GetCardDto getCard(UUID cardId);
}
