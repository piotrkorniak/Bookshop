package com.library.card.services;

import com.library.auth.dao.UserDao;
import com.library.book.dao.BookDao;
import com.library.card.dao.CardDao;
import com.library.card.dto.GetCardDto;
import com.library.card.dto.UpdateCardDto;
import com.library.card.services.interfaces.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardService implements ICardService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private CardDao cardDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public GetCardDto updateCard(UpdateCardDto updateCardDto) {
        return null;
    }

    @Override
    public void removeCard(UUID cardId) {

    }

    @Override
    public GetCardDto getCard(UUID cardId) {
        return new GetCardDto(cardDao.getById(cardId));
    }
}
