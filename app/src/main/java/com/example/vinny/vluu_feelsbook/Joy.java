package com.example.vinny.vluu_feelsbook;

import java.util.Date;

public class Joy extends Emotion {
    /*
     * Subclass of Emotion that implements the joy emotion. Contains 1 public Constructor
     * that takes in a date and sets the emotion date to the parameter date, while automatically
     * setting the emotion name to Joy.
     */

    public Joy(Date date) {
        this.emotionName = "Joy";
        this.emotionDate = date;
    }
}
