package com.techelevator.dao;

import com.techelevator.model.CollectionDto;

import java.util.List;

public interface CollectionDao {
    int getCollectionAuthor(int collectionId);
    CollectionDto createCollectionDto(CollectionDto collection, int authorId);
    CollectionDto getCollectionById(int collectionId);
    List<CollectionDto> getMagicCollections();
    List<CollectionDto> getPokemonCollections();
    List<CollectionDto> getMyCollections(int userId);
    List<CollectionDto> getFavoriteCollections(int userId);
    List<CollectionDto> getCollectionsByCardApiId(String cardApiId);
    void deleteCollection(int collectionId);
    void addCollectionToFavorite(int collectionId, int userId);
    void removeCollectionFromFavorite(int collectionId, int userId);
}
