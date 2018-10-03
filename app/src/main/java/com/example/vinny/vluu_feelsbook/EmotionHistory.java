package com.example.vinny.vluu_feelsbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class EmotionHistory implements Serializable{
    // EmotionHistory is a class that is an Arraylist that holds Emotions.

    public ArrayList<Emotion> emotionHistory;

    public EmotionHistory() {
        emotionHistory = new ArrayList<Emotion>();
    }

    public Collection<Emotion> getEmotionHistory() {
        return emotionHistory;
    }

    public void addEmotion(Emotion emotion){
        emotionHistory.add(emotion);
    }

    public void removeEmotion(Emotion emotion){
        emotionHistory.remove(emotion);
    }

    public int size() {
        return emotionHistory.size();
    }

    public void setAnEmotion(Emotion newEmotion, int index) {
        emotionHistory.set(index, newEmotion);
    }

    public int getIndex(Emotion emotion){
        return emotionHistory.indexOf(emotion);
    }

    public int count(Emotion emotion) {
        int count = 0;
        for (Emotion anEmotion : emotionHistory) {
            if (anEmotion.getClass().equals(emotion.getClass())){
                count++;
            }
        }
        return count;
    }

}
