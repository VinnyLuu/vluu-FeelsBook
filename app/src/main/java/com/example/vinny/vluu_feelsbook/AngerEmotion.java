package com.example.vinny.vluu_feelsbook;

import java.util.Calendar;
import java.util.Date;

public class AngerEmotion extends Emotion {

    public AngerEmotion(){
        Date date = Calendar.getInstance().getTime();
        emotionDate = date;
        emotionName = "Anger";
    }
}
