package com.example.vinny.vluu_feelsbook;

import java.util.Comparator;

public class EmotionComparator implements Comparator<Emotion> {
    public int compare(Emotion e1, Emotion e2){
        return e1.getEmotionDate().compareTo(e2.getEmotionDate());
    }
}
