package com.example.vinny.vluu_feelsbook;

import java.util.Calendar;
import java.util.Date;

public class LoveEmotion extends Emotion {

    public LoveEmotion() {
        Date date = Calendar.getInstance().getTime();
        emotionDate = date;
        emotionName = "Love";
    }
}
