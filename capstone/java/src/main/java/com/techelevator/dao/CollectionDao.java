package com.techelevator.dao;

import com.techelevator.model.CollectionDto;

import java.util.List;

public interface CollectionDao {
    CollectionDto getCollectionById(int collectionId);
    CollectionDto createCollectionDto(CollectionDto collection, int authorId);
    CollectionDto addCardToCollectionById(int collectionId, int cardId);
    int removeCardFromCollectionById(int collectionId, int cardId);
    List<CollectionDto> getMyCollections(int userId);
    List<CollectionDto> getFavCollections(int userId);
    void addCollectionToFavorite(int collectionId, int userId);
    void removeCollectionFromFavorite(int collectionId, int userId);
}
