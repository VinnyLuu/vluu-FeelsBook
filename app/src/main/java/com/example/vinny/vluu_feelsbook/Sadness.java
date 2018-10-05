package com.example.vinny.vluu_feelsbook;

import java.util.Date;

public class Sadness extends Emotion {
    /*
     * Subclass of Emotion that implements the sadness emotion. Contains 1 public Constructor
     * that takes in a date and sets the emotion date to the parameter date, while automatically
     * setting the emotion name to Sadness.
     */

    public Sadness(Date date) {
        this.emotionName = "Sadness";
        this.emotionDate = date;
    }
}
