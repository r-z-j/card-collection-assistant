package com.techelevator.model;

import java.math.BigDecimal;

public class CardDto {

    private int cardId;
    private String cardApiId;
    private String cardName;
    private BigDecimal userPrice;
    private int quantity;
    private int gameTypeId;
    private int conditionId;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getGameTypeId() {
        return gameTypeId;
    }

    public void setGameTypeId(int gameTypeId) {
        this.gameTypeId = gameTypeId;
    }

    public int getConditionId() {
        return conditionId;
    }

    public void setConditionId(int conditionId) {
        this.conditionId = conditionId;
    }

    public String getGameType() {
        switch(gameTypeId) {
            case 1: return "Magic The Gathering";
            case 2: return "Pokemon";
            default: return "Unknown";
        }
    }

    public String getCondition() {
        switch(conditionId) {
            case 1: return "Mint";
            case 2: return "Near Mint";
            case 3: return "Excellent";
            case 4: return "Good";
            case 5: return "Light Played";
            case 6: return "Played";
            case 7: return "Poor";
            default: return "Unknown";
        }
    }

}
