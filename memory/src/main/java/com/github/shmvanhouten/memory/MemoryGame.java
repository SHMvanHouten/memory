package com.github.shmvanhouten.memory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MemoryGame {
    private Map<Integer, Position> positions = new TreeMap<>();
    private ShuffleMachine shuffleMachine;

    public MemoryGame(int amountOfCards, Map<Integer, String> imageLocations, ShuffleMachine shufMachine){
        shuffleMachine = shufMachine;
        fillPositions(amountOfCards, imageLocations);
    }

    private void fillPositions(int amountOfCardFaces, Map<Integer, String> imageLocations){
        for (int i = 0; i < amountOfCardFaces; i++) {
            makePositionWithCard(imageLocations, i, amountOfCardFaces);
        }
    }

    private void makePositionWithCard(Map<Integer, String> imageLocations, int i, int amountOfCardFaces) {
        Position position = new Position();
        position.assignCard(imageLocations.get(i));
        positions.put(i, position);
        positions.put(i+amountOfCardFaces, position);
    }


    public boolean isPositionOccupied(int i) {
        return positions.get(i).isOccupied();
    }

    public Position getPosition(int positionIndex) {
        return positions.get(positionIndex);
    }
}
