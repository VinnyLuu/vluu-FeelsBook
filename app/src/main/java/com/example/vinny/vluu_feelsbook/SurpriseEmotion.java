package com.example.vinny.vluu_feelsbook;

import java.util.Calendar;
import java.util.Date;

public class SurpriseEmotion extends Emotion {

    public SurpriseEmotion() {
        Date date = Calendar.getInstance().getTime();
        emotionDate = date;
        emotionName = "Surprise";
    }
}
