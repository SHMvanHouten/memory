package com.github.shmvanhouten.memory;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void itShouldSayItIsOccupied() throws Exception {
        Position position = new Position();
        position.assignCard("1.jpg");
        assertThat(position.isOccupied(), is(true));
    }

    @Test
    public void itShouldTellIfTheCardOnThePositionIsTurned() throws Exception {
        Position position = new Position();
        position.assignCard("1.jpg");
        assertThat(position.getCard().isTurned, is(false));
        position.getCard().turn();
        assertThat(position.getCard().isTurned, is(true));
    }
}