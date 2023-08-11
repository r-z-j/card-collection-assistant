package com.techelevator.dao;

import com.techelevator.model.CardDto;

public interface CardDao {

    CardDto getCardById(int cardId);
    int addCard(CardDto card);
}
