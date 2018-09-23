package com.example.vinny.vluu_feelsbook;

import java.util.Calendar;
import java.util.Date;

public class JoyEmotion extends Emotion {

    public JoyEmotion() {
        Date date = Calendar.getInstance().getTime();
        emotionDate = date;
        emotionName = "Joy";
    }
}
