package com.github.shmvanhouten.memory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MemoryGame {
    private Map<Integer, Position> positions = new TreeMap<>();

    public MemoryGame(int amountOfCards, Map<Integer, String> imageLocations, ShuffleMachine shuffleMachine){
        fillPositions(amountOfCards, imageLocations);
    }

    private void fillPositions(int amountOfCards, Map<Integer, String> imageLocations){
        for (int i = 0; i < amountOfCards; i++) {
            makePositionWithCard(imageLocations, i);
        }
    }

    private void makePositionWithCard(Map<Integer, String> imageLocations, int i) {
        Position position = new Position();
        position.assignCard(imageLocations.get(i));
        positions.put(assignPosition(), position);
    }

    private Integer assignPosition() {
        return null;
    }

    public boolean isPositionOccupied(int i) {
        return positions.get(i).isOccupied();
    }

    public Position getPosition(int positionIndex) {
        return positions.get(positionIndex);
    }
}
