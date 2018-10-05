package com.example.vinny.vluu_feelsbook;

import java.util.Date;

public class Fear extends Emotion {
    /*
     * Subclass of Emotion that implements the fear emotion. Contains 1 public Constructor
     * that takes in a date and sets the emotion date to the parameter date, while automatically
     * setting the emotion name to Fear.
     */

    public Fear(Date date) {
        this.emotionName = "Fear";
        this.emotionDate = date;
    }
}
