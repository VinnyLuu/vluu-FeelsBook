package com.example.vinny.vluu_feelsbook;

import java.util.Calendar;
import java.util.Date;

public class SadnessEmotion extends Emotion {

    public SadnessEmotion() {
        Date date = Calendar.getInstance().getTime();
        emotionDate = date;
        emotionName = "Sadness";
    }
}
