package com.example.vinny.vluu_feelsbook;

import java.util.Comparator;

public class EmotionComparator implements Comparator<Emotion> {
    /*
     * EmotionComparator is a custom Comparator class that is used to sort the emotion history
     * by date in ascending order.
     */

    /* Method used to compare two different emotions using their dates */
    public int compare(Emotion e1, Emotion e2) {
        return e1.getEmotionDate().compareTo(e2.getEmotionDate());
    }
}
