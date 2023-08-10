package com.techelevator.model;

import java.util.List;
import java.util.Objects;

public class CollectionDto {

    private int collectionId;
    private String collectionName;
    private int authorId;
    private GameType gameType;
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

    public int getGameTypeId() {
        return gameType.getId();
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(int id) {
        switch (id) {
            case 1: this.gameType = GameType.MAGIC;
                break;
            case 2: this.gameType = GameType.POKEMON;
                break;
            default: this.gameType = GameType.UNKNOWN;
        }
    }

    public List<CardDto> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardDto> cardList) {
        this.cardList = cardList;
    }

    @Override
    public String toString() {
        return "CollectionDto{" +
                "collectionId=" + collectionId +
                ", collectionName='" + collectionName + '\'' +
                ", authorId=" + authorId +
                ", gameType=" + gameType +
                ", cardList=" + cardList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionDto that = (CollectionDto) o;
        return collectionId == that.collectionId && authorId == that.authorId && Objects.equals(collectionName, that.collectionName) && gameType == that.gameType && Objects.equals(cardList, that.cardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionId, collectionName, authorId, gameType, cardList);
    }
}
