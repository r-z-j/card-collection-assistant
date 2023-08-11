package com.techelevator.dao;

import com.techelevator.model.CardDto;

import java.util.List;

public interface CardDao {

    CardDto getCardById(int cardId);
    int addCard(CardDto card);
    List<CardDto> getAllCards();
}
