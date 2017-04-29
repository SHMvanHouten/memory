package com.github.shmvanhouten.memory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MemoryGameTest {

    @Test
    public void itShouldTellIfAPositionIsOccupied() throws Exception {
        MemoryGame game = makeMemoryGame();
        assertThat(game.isPositionOccupied(0), is(true));
    }

    @Test
    public void itShouldSayIfTheCardInThePositionIsTurned() throws Exception {
        MemoryGame game = makeMemoryGame();
        assertThat(game.getPosition(0).getCard().isTurned(), is(false));
        game.getPosition(0).getCard().turn();
        assertThat(game.getPosition(0).getCard().isTurned(), is(true));
        assertThat(game.getPosition(1).getCard().isTurned(), is(false));
    }


    @Test
    public void itShouldMakeTwoOfEachCards() throws Exception {
        MemoryGame game = makeMemoryGame();
        MemoryCard cardAtZero = game.getPosition(0).getCard();
        MemoryCard cardAt16 = game.getPosition(16).getCard();

        assertThat(cardAtZero.equals(cardAt16), is(true));

        MemoryCard cardAt1 = game.getPosition(1).getCard();

        assertThat(cardAtZero.equals(cardAt1), is(false));
    }

    @Test
    public void itShouldCompareTwoSelectedPositions() throws Exception {
        MemoryGame game = makeMemoryGame();
        game.selectPosition(0);
        game.selectPosition(1);

        assertThat(game.isPositionOccupied(0), is(true));

        game.selectPosition(0);
        game.selectPosition(16);
        assertThat(game.isPositionOccupied(0), is(false));
    }

    @Test
    public void itShouldSayWhoseTurnItIS() throws Exception {
        MemoryGame game = makeMemoryGame();
        assertThat(game.getCurrentPlayer().getName(), is("Sjoerd"));

        game.selectPosition(0);
        game.selectPosition(1);
        assertThat(game.getCurrentPlayer().getName(),is("Pietje"));
        game.selectPosition(0);
        game.selectPosition(0);
        assertThat(game.getCurrentPlayer().getName(), is("Pietje"));
    }

    @Test
    public void itShouldGiveCurrentPlayerAPoint() throws Exception {
        MemoryGame game = makeMemoryGame();
        assertThat(game.getCurrentPlayer().getScore(), is(0));
        game.selectPosition(0);
        game.selectPosition(16);
        assertThat(game.getCurrentPlayer().getName(), is("Sjoerd"));
        assertThat(game.getCurrentPlayer().getScore(), is(1));

    }

    @Test
    public void itShouldShuffleTheCards() throws Exception {
        MemoryGame game = makeMemoryGame();
        game.selectPosition(2);
        game.selectPosition(18);
        assertThat(game.getCurrentPlayer().getName(), is("Sjoerd"));
        assertThat(game.getCurrentPlayer().getScore(), is(1));
    }

    @Test
    public void itShouldShufflePlayers() throws Exception {
        MemoryGame game = makeMemoryGame();
        String currentPlayer = game.getCurrentPlayer().getName();

        assertThat(currentPlayer, is("Sjoerd"));

        game.selectPosition(1);
        game.selectPosition(2);
        currentPlayer = game.getCurrentPlayer().getName();
        assertThat(currentPlayer, is("Pietje"));

    }

    private Map<Integer, String> createListOfCards() {
        Map<Integer, String> listOfCards = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            listOfCards.put(i, (i + 1) + ".jpg");
        }
        return listOfCards;
    }

    private MemoryGame makeMemoryGame() {
        Map<Integer, String> listOfCards = createListOfCards();
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Sjoerd");
        Player player2 = new Player("Pietje");
        players.add(player1);
        players.add(player2);
//        ShuffleMachine shuffleMachine = new ShuffleMachine();
//        return new MemoryGame(16, listOfCards, shuffleMachine, players);
        return new MemoryGame(16, listOfCards, players);
    }
}
