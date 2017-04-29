package com.github.shmvanhouten.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MemoryGame {
    private Map<Integer, Position> positions = new TreeMap<>();
    private List<Player> players;
    private boolean firstPick = true;
    private Position firstPickedPosition;
    private Integer playerTurn = 0;

    public MemoryGame(int amountOfCards, Map<Integer, String> imageLocations, ShuffleMachine shuffleMachine, List<Player> playerList){
        players = shuffleMachine.shuffle(playerList, true);
        Map<Integer, Integer>shuffledPositions = shuffleMachine.shuffle(amountOfCards * 2, true);
        fillPositions(amountOfCards, imageLocations, shuffledPositions);
    }


    private void fillPositions(int amountOfCardFaces, Map<Integer, String> imageLocations, Map<Integer, Integer> shuffledPositions){
        for (int i = 0; i < amountOfCardFaces; i++) {
            makePositionWithCard(imageLocations, i, amountOfCardFaces, shuffledPositions);
        }
    }

    private void makePositionWithCard(Map<Integer, String> imageLocations, int i, int amountOfCardFaces, Map<Integer, Integer> shuffledPositions) {
        String image = imageLocations.get(i);
        Position position = new Position();
        position.assignCard(image);
        Position copiedPosition = new Position();
        copiedPosition.assignCard(image);
        positions.put(shuffledPositions.get(i), position);
        positions.put(shuffledPositions.get(i+amountOfCardFaces), copiedPosition);
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
        System.out.println("Wrong! Opponents turn!");

        firstPickedPosition.getCard().turn();
        secondPickedPosition.getCard().turn();

        switchPlayerTurn();

        firstPick = true;
    }

    private void switchPlayerTurn() {
        if(playerTurn == players.size()){
            playerTurn = 0;
        }else{
            playerTurn++;
        }
    }

    private void handleSituationIfCardsAreEqual(Position secondPickedPosition) {
        System.out.println("Good job!");

        firstPickedPosition.setUnoccupied();
        secondPickedPosition.setUnoccupied();

        getCurrentPlayer().scorePoint();

        firstPick = true;
    }

    public Player getCurrentPlayer() {
        return players.get(playerTurn);
    }
}
