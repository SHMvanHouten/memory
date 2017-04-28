package com.github.shmvanhouten.memory;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MemoryCardTest {

    @Test
    public void itShouldTellIfACardIsTurnedOrNot() throws Exception {
        MemoryCard card = new MemoryCard("1.jpg");
        assertThat(card.isTurned, is(false));
        card.turn();
        assertThat(card.isTurned, is(true));
    }


}