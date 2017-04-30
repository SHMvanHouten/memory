package com.github.shmvanhouten.memory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MemoryGame {
    private Map<Integer, Position> positions;
    private List<Player> players;
    private boolean firstPick = true;
    private Position firstPickedPosition;
    private Integer playerTurn = 0;

    public MemoryGame(int amountOfCards, Map<Integer, String> imageLocations, ShuffleMachine shuffleMachine, List<Player> playerList){
        players = shuffleMachine.shuffle(playerList);
        Map<Integer, Position>unShuffledPositions = fillPositions(amountOfCards, imageLocations);
        positions = shuffleMachine.shuffle(unShuffledPositions);
    }

    public MemoryGame(int amountOfCards, Map<Integer, String> imageLocations, List<Player>playerList){
        players = playerList;
        positions = fillPositions(amountOfCards, imageLocations);
    }

    public static void main(String[] args) {
        Map<Integer, String> listOfCards = createListOfCards();
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Sjoerd");
        Player player2 = new Player("Pietje");
        players.add(player1);
        players.add(player2);
        ShuffleMachine shuffleMachine = new ShuffleMachine();
        MemoryGame game = new MemoryGame(16, listOfCards, shuffleMachine, players);
//        MemoryGame game =new MemoryGame(16, listOfCards, players);
        game.startInputStream(game);
    }

    private void startInputStream(MemoryGame game) {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);

        try(BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String inputLine = reader.readLine();
            while(inputLine != null){
                game.selectPosition(Integer.parseInt(inputLine));
                inputLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<Integer, Position> fillPositions(int amountOfCardFaces, Map<Integer, String> imageLocations){
        Map<Integer, Position> unShuffledPositions = new TreeMap<>();
        for (int i = 0; i < amountOfCardFaces; i++) {
            String image = imageLocations.get(i);

            Position position = new Position();
            position.assignCard(image);
            unShuffledPositions.put(i, position);

            Position copiedPosition = new Position();
            copiedPosition.assignCard(image);
            unShuffledPositions.put(i+amountOfCardFaces, copiedPosition);
        }
        return unShuffledPositions;
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
            System.out.println(getCurrentPlayer().getName() + ", please pick another card.");
        }else if(card.isTurned()){
            System.out.println("Card has already been turned");
        }else{
            card.turn();
            compareCards(position);
            System.out.println(getCurrentPlayer().getName() + ", pick your first card!");
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

        firstPickedPosition.getCard().turn();
        secondPickedPosition.getCard().turn();

        switchPlayerTurn();

        System.out.println("Wrong! "+ getCurrentPlayer().getName() + "'s turn!");

        firstPick = true;
    }

    private void switchPlayerTurn() {
        if(playerTurn == players.size() -1){
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
        System.out.println(getCurrentPlayer().getName() + " has " +getCurrentPlayer().getScore() + " points");

        firstPick = true;
    }

    public Player getCurrentPlayer() {
        return players.get(playerTurn);
    }

    private static Map<Integer, String> createListOfCards() {
        Map<Integer, String> listOfCards = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            listOfCards.put(i, (i + 1) + ".jpg");
        }
        return listOfCards;
    }
}
