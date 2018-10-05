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

public class EmotionHistory implements Serializable {
    /*
     * EmotionHistory is a class that is used to instantiate a custom EmotionHistory object
     * that is an Arraylist that holds instances of the custom class Emotion. It contains a
     * getter for the emotion history, along with methods to obtain information about the
     * emotions within the EmotionHistory, manipulate the emotions within the EmotionHistory,
     * and both save and load the emotion from the file where it is saved.
     */


    private ArrayList<Emotion> emotionHistory;

    /* Constructor that creates a new custom array list of Emotion */
    public EmotionHistory() {
        emotionHistory = new ArrayList<Emotion>();
    }

    /* Getter of EmotionHistory */
    public Collection<Emotion> getEmotionHistory() {
        return emotionHistory;
    }

    /* Method used to add an Emotion to the EmotionHistory */
    public void addEmotion(Emotion emotion) {
        emotionHistory.add(emotion);
    }

    /* Method used to remove an Emotion to the EmotionHistory */
    public void removeEmotion(Emotion emotion) {
        emotionHistory.remove(emotion);
    }

    /* Method used to get size of the EmotionHistory */
    public int size() {
        return emotionHistory.size();
    }

    /* Method used to set an existing Emotion in the EmotionHistory to a new Emotion */
    public void setAnEmotion(Emotion newEmotion, int index) {
        emotionHistory.set(index, newEmotion);
    }

    /* Method used to get the index of a specified Emotion from the EmotionHistory */
    public int getIndex(Emotion emotion) {
        return emotionHistory.indexOf(emotion);
    }

    /* Method used to count all instances of the specific emotion class from the EmotionHistory */
    public int count(Emotion emotion) {
        int count = 0;
        for (Emotion anEmotion : emotionHistory) {
            if (anEmotion.getClass().equals(emotion.getClass())) {
                count++;
            }
        }
        return count;
    }

    /* Method used to load the EmotionHistory from the save file*/
    public EmotionHistory loadFromFile(String FILENAME, Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Emotion.class, new EmotionJsonAdapter());
            Gson gson = builder.create();
            Type emotionHistoryType = new TypeToken<EmotionHistory>() {
            }.getType();
            EmotionHistory emotionHistory = gson.fromJson(reader, emotionHistoryType);
            return emotionHistory;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            EmotionHistory emotionHistory = new EmotionHistory();
            return emotionHistory;
        }

    }

    /* Method used to save the EmotionHistory into the save file*/
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
