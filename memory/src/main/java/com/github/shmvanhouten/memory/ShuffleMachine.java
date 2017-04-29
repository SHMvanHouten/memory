package com.github.shmvanhouten.memory;

import java.util.HashMap;
import java.util.Map;

public class ShuffleMachine {
    public Map<Integer,Integer> shuffle(int numberToShuffle, boolean brokenShuffleMachine) {
        Map<Integer, Integer> shuffledMap = new HashMap<>();

        if(brokenShuffleMachine) {
            for (int i = 0; i < numberToShuffle; i++) {
                shuffledMap.put(i, i);
            }
            shuffledMap.put(3, 18);
        }

        return shuffledMap;
    }


}

