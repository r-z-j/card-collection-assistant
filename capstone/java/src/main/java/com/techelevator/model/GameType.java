package com.techelevator.model;

public enum GameType {
    UNKNOWN(0),
    MAGIC(1),
    POKEMON(2);

    private final int id;

    GameType(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
