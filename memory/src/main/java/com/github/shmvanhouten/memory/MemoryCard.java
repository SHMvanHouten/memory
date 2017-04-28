package com.github.shmvanhouten.memory;

import java.io.File;

public class MemoryCard {
    private static File backOfCard = new File("D:\\mijn documenten\\simonekamer.github.io\\images", "achterkant.jpg");
    private File image;
    boolean isTurned = false;

    MemoryCard(String imageLocation){
        image = new File("D:\\mijn documenten\\simonekamer.github.io\\images", imageLocation);
    }

    public void turn() {
        isTurned = !isTurned;
    }
}
