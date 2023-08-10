package com.techelevator.model;

public enum CardCondition {
    MINT(1),
    NEAR_MINT(2),
    EXCELLENT(3),
    GOOD(4),
    LIGHT_PLAYED(5),
    PLAYED(6),
    POOR(7);

    private final int id;

    CardCondition(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

}
