package com.example.vinny.vluu_feelsbook;

import java.util.Date;

public class Anger extends Emotion {
    /*
     * Subclass of Emotion that implements the anger emotion. Contains 1 public Constructor
     * that takes in a date and sets the emotion date to the parameter date, while automatically
     * setting the emotion name to Anger.
     */

    public Anger(Date date) {
        this.emotionName = "Anger";
        this.emotionDate = date;

    }
}
