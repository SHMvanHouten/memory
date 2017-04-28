package com.github.shmvanhouten.memory;

import java.io.File;

public class Position {
    private MemoryCard card;
    private boolean occupied = false;

    public void assignCard(String imageLocation) {
        card = new MemoryCard(imageLocation);
        occupied = true;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public MemoryCard getCard() {
        return card;
    }
}
