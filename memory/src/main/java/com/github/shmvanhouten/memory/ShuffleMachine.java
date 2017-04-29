package com.github.shmvanhouten.memory;


import java.util.*;

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
        if(brokenShuffleMachine) {
            return playerList;
        }
        Collections.shuffle(playerList);
        return playerList;
    }

    public Map<Integer, Position> shuffle(Map<Integer, Position> unShuffledMap, boolean brokenShuffleMachine){
        if(brokenShuffleMachine){
            return unShuffledMap;
        }
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

