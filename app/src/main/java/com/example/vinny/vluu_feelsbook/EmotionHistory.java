package com.example.vinny.vluu_feelsbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class EmotionHistory implements Serializable{

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

    public int count(String emotionName) {
        int count = 0;
        for (Emotion emotion : emotionHistory) {
            if (emotion.getEmotionName().equals(emotionName)){
                count++;
            }
        }
        return count;
    }

}
