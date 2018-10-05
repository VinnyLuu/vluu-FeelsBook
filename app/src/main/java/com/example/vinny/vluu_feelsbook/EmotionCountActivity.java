package com.example.vinny.vluu_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class EmotionCountActivity extends AppCompatActivity {
    /*
     * EmotionCountActivity is the activity used to display the counts of each emotion. It sets
     * up the views to display the counts and calls on EmotionHistory's count method to collect
     * the counts of each emotion.
     */

    private static final String FILENAME = "filetest.sav";
    private TextView joyCountView;
    private TextView sadCountView;
    private TextView angerCountView;
    private TextView surpriseCountView;
    private TextView loveCountView;
    private TextView fearCountView;
    private EmotionHistory emotionHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_emotion);

        /* Set up the emotion history list and views to display the counts */
        emotionHistory = new EmotionHistory().loadFromFile(FILENAME, this);
        joyCountView = findViewById(R.id.joyCountView);
        sadCountView = findViewById(R.id.sadCountView);
        angerCountView = findViewById(R.id.angerCountView);
        surpriseCountView = findViewById(R.id.surpriseCountView);
        loveCountView = findViewById(R.id.loveCountView);
        fearCountView = findViewById(R.id.fearCountView);

        /* Get counts of each emotion and set them to the correct view */
        angerCountView.setText(String.valueOf(emotionHistory.count(new Anger(new Date()))));
        joyCountView.setText(String.valueOf(emotionHistory.count(new Joy(new Date()))));
        sadCountView.setText(String.valueOf(emotionHistory.count(new Sadness(new Date()))));
        surpriseCountView.setText(String.valueOf(emotionHistory.count(new Surprise(new Date()))));
        fearCountView.setText(String.valueOf(emotionHistory.count(new Fear(new Date()))));
        loveCountView.setText(String.valueOf(emotionHistory.count(new Love(new Date()))));

    }

}
