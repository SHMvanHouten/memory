package com.github.shmvanhouten.memory;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MemoryCardTest {

    @Test
    public void itShouldTellIfACardIsTurnedOrNot() throws Exception {
        MemoryCard card = new MemoryCard("1.jpg");
        assertThat(card.isTurned(), is(false));
        card.turn();
        assertThat(card.isTurned(), is(true));
    }

    @Test
    public void itShouldCompare2Cards() throws Exception {
        MemoryCard card = new MemoryCard("1.jpg");
        MemoryCard cardCopy = new MemoryCard("1.jpg");
        MemoryCard card2 = new MemoryCard("2.jpg");
        assertThat(card.equals(cardCopy), is(true));
        assertThat(card.equals(card2), is(false));
    }

}