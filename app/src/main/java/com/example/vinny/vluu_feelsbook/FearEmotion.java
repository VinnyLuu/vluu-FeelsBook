package com.example.vinny.vluu_feelsbook;

import java.util.Calendar;
import java.util.Date;

public class FearEmotion extends Emotion {

    public FearEmotion() {
        Date date = Calendar.getInstance().getTime();
        emotionDate = date;
        emotionName = "Fear";
    }
}
