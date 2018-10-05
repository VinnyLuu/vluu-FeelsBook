package com.example.vinny.vluu_feelsbook;

import java.util.Date;

public class Love extends Emotion {
    /*
     * Subclass of Emotion that implements the love emotion. Contains 1 public Constructor
     * that takes in a date and sets the emotion date to the parameter date, while automatically
     * setting the emotion name to Love.
     */

    public Love(Date date) {
        this.emotionName = "Love";
        this.emotionDate = date;
    }
}
