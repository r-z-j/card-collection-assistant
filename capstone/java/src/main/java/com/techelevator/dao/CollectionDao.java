package com.techelevator.dao;

import com.techelevator.model.CardDto;
import com.techelevator.model.CollectionDto;

import java.util.List;

public interface CollectionDao {
    CollectionDto getCollectionById(int collectionId);
    CollectionDto createCollectionById(CollectionDao collection);
    CollectionDto updateCollectionById(int collectionId);
    CollectionDto addCardToCollectionById(int collectionId, CardDto card);
    CollectionDto removeCardFromCollectionById(int collectionId, CardDto card);
    List<CollectionDao> getCollectionsByUserId(int userId);
    List<CardDto> getCardsInCollectionById(int collectionId);
}
