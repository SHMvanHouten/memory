package com.github.shmvanhouten.memory;


import java.util.*;

public class ShuffleMachine {


    public List<Player> shuffle(List<Player> playerList) {
        Collections.shuffle(playerList);
        return playerList;
    }

    public Map<Integer, Position> shuffle(Map<Integer, Position> unShuffledMap){
        Map<Integer, Position> shuffledMap = new TreeMap<>();
        List<Integer> integers = new ArrayList<>(unShuffledMap.keySet());
        Collections.shuffle(integers);
        int i = 0;
        for (Integer integer : integers) {
            shuffledMap.put(i, unShuffledMap.get(integer));
            i++;
        }
        return shuffledMap;
    }
}

