package com.techelevator.model;

import java.math.BigDecimal;
import java.util.Objects;

public class CardDto {

    private int cardId;
    private String cardApiId;
    private String cardName;
    private BigDecimal userPrice;
    private CardCondition condition;
    private int quantity;
    private GameType gameType;

    public CardDto() {};

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

    public BigDecimal getUserPrice() {
        return userPrice;
    }

    public void setUserPrice(BigDecimal userPrice) {
        this.userPrice = userPrice;
    }

    public CardCondition getCondition() {
        return condition;
    }

    public void setCondition(CardCondition condition) {
        this.condition = condition;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public GameType getGameType() {
        return gameType;
    }

    public int getGametypeId() {
        return gameType.getId();
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

    @Override
    public String toString() {
        return "CardDto{" +
                "cardId=" + cardId +
                ", cardApiId='" + cardApiId + '\'' +
                ", cardName='" + cardName + '\'' +
                ", userPrice=" + userPrice +
                ", condition=" + condition +
                ", quantity=" + quantity +
                ", gameType=" + gameType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDto cardDto = (CardDto) o;
        return cardId == cardDto.cardId && quantity == cardDto.quantity && Objects.equals(cardApiId, cardDto.cardApiId) && Objects.equals(cardName, cardDto.cardName) && Objects.equals(userPrice, cardDto.userPrice) && condition == cardDto.condition && gameType == cardDto.gameType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, cardApiId, cardName, userPrice, condition, quantity, gameType);
    }
}
