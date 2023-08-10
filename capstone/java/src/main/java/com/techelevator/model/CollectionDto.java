package com.techelevator.model;

import java.util.List;
import java.util.Objects;

public class CollectionDto {

    private int collectionId;
    private String collectionName;
    private int authorId;
    private Gametype gametype;
    private int gameTypeId;
    private List<String> cardApiList;

    public CollectionDto(int collectionId, String collectionName, int authorId, int gameTypeId) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.authorId = authorId;
        this.gameTypeId = gameTypeId;
        switch (gameTypeId) {
            case 1: this.gametype = Gametype.MAGIC;
                break;
            case 2: this.gametype = Gametype.POKEMON;
                break;
        }
    }

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

    public Gametype getGametype() {
        return gametype;
    }

    public int getGameTypeId() {
        if (gametype == Gametype.MAGIC) return 1;
        if (gametype == Gametype.POKEMON) return 2;
        else return gameTypeId;
    }

    public void setCardApiList(List<String> cardApiList) {
        this.cardApiList = cardApiList;
    }

    public List<String> getCardApiList() {
        return cardApiList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionDto that = (CollectionDto) o;
        return collectionId == that.collectionId && authorId == that.authorId && Objects.equals(collectionName, that.collectionName) && gametype == that.gametype;
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionId, collectionName, authorId, gametype);
    }

    @Override
    public String toString() {
        return "CollectionDto{" +
                "collectionId=" + collectionId +
                ", collectionName='" + collectionName + '\'' +
                ", authorId=" + authorId +
                ", gametype=" + gametype +
                ", cardApiList=" + cardApiList +
                '}';
    }
}
