package com.github.shmvanhouten.memory;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ShuffleMachineTest {

    @Test
    public void itShouldShuffle() throws Exception {
        ShuffleMachine shuffleMachine = new ShuffleMachine();
        Map<Integer,Integer> testMap = new HashMap<>();
        testMap.put(0,0);
        testMap.put(1,1);
        assertThat(shuffleMachine.shuffle(2, true).get(0), is(0));
        assertThat(shuffleMachine.shuffle(2, true).get(1), is(1));
    }
}