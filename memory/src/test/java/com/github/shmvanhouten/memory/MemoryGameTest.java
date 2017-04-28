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
        assertThat(game.getPosition(0).getCard().isTurned, is(false));
        game.getPosition(0).getCard().turn();
        assertThat(game.getPosition(0).getCard().isTurned, is(true));
        assertThat(game.getPosition(1).getCard().isTurned, is(false));
    }


    @Test
    public void itShouldMakeTwoOfEachCards() throws Exception {
        MemoryGame game = makeMemoryGame();
        MemoryCard cardAtZero = game.getPosition(0).getCard();
        assertThat(cardAtZero.equals(game.getPosition(16).getCard()), is(true));
        assertThat(cardAtZero.equals(game.getPosition(1).getCard()), is(false));
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
        ShuffleMachine shuffleMachine = new ShuffleMachine();
        return new MemoryGame(16, listOfCards, shuffleMachine);
    }
}
