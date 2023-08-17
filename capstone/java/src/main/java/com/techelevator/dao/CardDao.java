package com.techelevator.dao;

import com.techelevator.model.CardDto;

import java.util.List;

public interface CardDao {
    CardDto getCardById(int cardId);
    CardDto updateCard(int cardId, CardDto card);
    List<CardDto> getAllCards();
    int addCard(CardDto card);
    void removeCard(String cardId);
}
