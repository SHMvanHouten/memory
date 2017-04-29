package com.github.shmvanhouten.memory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ShuffleMachineTest {

    @Test
    public void itShouldShuffle() throws Exception {
        ShuffleMachine shuffleMachine = new ShuffleMachine();
        List<Player> testMap = new ArrayList<>();
        testMap.add(new Player("Sjoerd"));
        testMap.add(new Player("Pietje"));
        assertThat(shuffleMachine.shuffle(testMap).get(0).getName(), is("Sjoerd"));
        assertThat(shuffleMachine.shuffle(testMap).get(1).getName(), is("Pietje"));
    }
}