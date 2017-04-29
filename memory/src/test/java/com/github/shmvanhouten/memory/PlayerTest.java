package com.github.shmvanhouten.memory;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void itShouldRecordAndGiveThePlayerName() throws Exception {
        Player sjoerd = new Player("Sjoerd");
        assertThat(sjoerd.getName(), is("Sjoerd"));
    }

    @Test
    public void itShouldAddOneToPlayerScore() throws Exception {
        Player player1 = new Player("Sjoerd");
        assertThat(player1.getScore(), is(0));
        player1.scorePoint();
        assertThat(player1.getScore(), is(1));
    }

}