package com.techelevator.model;

import java.util.List;

public class CollectionDto {

    private int collectionId;
    private String collectionName;
    private int authorId;
    private int gameTypeId;
    private List<CardDto> cardList;

    public CollectionDto() {};

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setGameTypeId(int gameTypeId) {
        this.gameTypeId = gameTypeId;
    }

    public int getGameTypeId() {
        return gameTypeId;
    }

    public String getGameType() {
        switch(gameTypeId) {
            case 1: return "Magic The Gathering";
            case 2: return "Pokemon";
            default: return "Unknown";
        }
    }

    public List<CardDto> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardDto> cardList) {
        this.cardList = cardList;
    }

}
