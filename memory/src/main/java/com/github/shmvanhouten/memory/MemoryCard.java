package com.github.shmvanhouten.memory;

import java.io.File;

public class MemoryCard {
    private static File backOfCard = new File("D:\\mijn documenten\\simonekamer.github.io\\images", "achterkant.jpg");
    private File image;
    private boolean isTurned = false;



    MemoryCard(String imageLocation){
        image = new File("D:\\mijn documenten\\simonekamer.github.io\\images", imageLocation);
    }

    public void turn() {
        isTurned = !isTurned;
    }

    public boolean isTurned() {
        return isTurned;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemoryCard that = (MemoryCard) o;

        return image.equals(that.image);
    }

    @Override
    public int hashCode() {
        return image.hashCode();
    }
}
