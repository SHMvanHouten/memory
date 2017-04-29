package com.github.shmvanhouten.memory;

public class Player {
    private final String name;
    private int playerScore = 0;


    public Player(String playerName){
        name = playerName;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return playerScore;
    }

    public void scorePoint() {
        playerScore++;
    }
}
