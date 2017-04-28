package com.github.shmvanhouten.memory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MemoryGame {
    private Map<Integer, Position> positions = new TreeMap<>();
    private ShuffleMachine shuffleMachine;
    private boolean firstPick = true;
    private Position firstPickedPosition;

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

    public void selectPosition(int i) {
        Position position = positions.get(i);
        MemoryCard card = position.getCard();
        if (!position.isOccupied()){
            System.out.println("no Card in this position");
        }else if(firstPick){
            card.turn();
            firstPick = false;
            firstPickedPosition = position;
        }else if(card.isTurned()){
            System.out.println("Card has already been turned");
        }else{
            card.turn();
            compareCards(position);
        }

    }

    private void compareCards(Position secondPickedPosition) {
        MemoryCard firstCard = firstPickedPosition.getCard();
        MemoryCard secondCard = secondPickedPosition.getCard();

        if(firstCard.equals(secondCard)){
            handleSituationIfCardsAreEqual(secondPickedPosition);
        }else{
            handleSituationIfCardsAreDifferent(secondPickedPosition);
        }
    }

    private void handleSituationIfCardsAreDifferent(Position secondPickedPosition) {
        System.out.println("Try again!");

        firstPickedPosition.getCard().turn();
        secondPickedPosition.getCard().turn();

        //todo: switch players.

        firstPick = false;
    }

    private void handleSituationIfCardsAreEqual(Position secondPickedPosition) {
        System.out.println("Good job!");

        firstPickedPosition.setUnoccupied();
        secondPickedPosition.setUnoccupied();

        //todo: give point to player

        firstPick = false;
    }
}
