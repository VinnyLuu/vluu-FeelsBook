package com.example.vinny.vluu_feelsbook;

import java.util.Date;

public class Surprise extends Emotion {
    /*
     * Subclass of Emotion that implements the surprise emotion. Contains 1 public Constructor
     * that takes in a date and sets the emotion date to the parameter date, while automatically
     * setting the emotion name to Surprise.
     */

    public Surprise(Date date) {
        this.emotionName = "Surprise";
        this.emotionDate = date;
    }
}
