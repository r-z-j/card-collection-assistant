package com.techelevator.dao;

import com.techelevator.model.CardDto;
import com.techelevator.model.CollectionDto;

import java.util.List;

public interface CollectionDao {
    CollectionDto getCollectionById(int collectionId);
    CollectionDto createCollectionDto(CollectionDto collection);
    CollectionDto updateCollectionById(int collectionId);
    CollectionDto addCardToCollectionById(int collectionId, int cardId);
    int removeCardFromCollectionById(int collectionId, int cardId);
    List<CollectionDao> getCollectionsByUserId(int userId);
    List<CardDto> getCardsInCollectionById(int collectionId);
}
