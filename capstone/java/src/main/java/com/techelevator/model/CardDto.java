package com.techelevator.model;

import java.util.Objects;

public class CardDto {

    private int cardId;
    private String cardApiId;
    private String cardName;
    private Gametype gametype;
    // private BigDecimal price/price range?

    public CardDto(int cardId, String cardApiId, String cardName, Gametype gametype) {
        this.cardId = cardId;
        this.cardApiId = cardApiId;
        this.cardName = cardName;
        this.gametype = gametype;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardApiId() {
        return cardApiId;
    }

    public void setCardApiId(String cardApiId) {
        this.cardApiId = cardApiId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Gametype getGametype() {
        return gametype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDto cardDto = (CardDto) o;
        return cardId == cardDto.cardId && Objects.equals(cardApiId, cardDto.cardApiId) && Objects.equals(cardName, cardDto.cardName) && gametype == cardDto.gametype;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, cardApiId, cardName, gametype);
    }

    @Override
    public String toString() {
        return "CardDto{" +
                "cardId=" + cardId +
                ", cardApiId='" + cardApiId + '\'' +
                ", cardName='" + cardName + '\'' +
                ", gametype=" + gametype +
                '}';
    }
}
