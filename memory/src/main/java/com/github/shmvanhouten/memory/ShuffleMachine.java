package com.github.shmvanhouten.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShuffleMachine {
    public Map<Integer,Integer> shuffle(int numberToShuffle, boolean brokenShuffleMachine) {
        Map<Integer, Integer> shuffledMap = new HashMap<>();

        if(brokenShuffleMachine) {
            for (int i = 0; i < numberToShuffle; i++) {
                shuffledMap.put(i, i);
            }
        }

        return shuffledMap;
    }


    public List<Player> shuffle(List<Player> playerList, boolean brokenShuffleMachine) {
        List<Player> shuffledList = new ArrayList<>();
        if(brokenShuffleMachine) {
            shuffledList = playerList;
        }
        return shuffledList;
    }
}

