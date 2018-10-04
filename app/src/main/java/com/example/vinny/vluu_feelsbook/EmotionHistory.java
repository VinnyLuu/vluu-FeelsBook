package com.example.vinny.vluu_feelsbook;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
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

    public EmotionHistory loadFromFile(String FILENAME, Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Emotion.class, new EmotionJsonAdapter());
            Gson gson = builder.create();
            Type emotionHistoryType = new TypeToken<EmotionHistory>(){}.getType();
            EmotionHistory emotionHistory = gson.fromJson(reader, emotionHistoryType);
            return emotionHistory;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            EmotionHistory emotionHistory = new EmotionHistory();
            return emotionHistory;
        }

    }

    public void saveInFile(String FILENAME, Context context, EmotionHistory emotionHistory) {
        try {
            // FILE OUTPUTSTREAM makes a byte stream
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            // Char stream
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            // Buffer stream
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Emotion.class, new EmotionJsonAdapter()).create();
            gson.toJson(emotionHistory, writer);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
