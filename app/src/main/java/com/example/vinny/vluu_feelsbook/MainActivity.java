package com.example.vinny.vluu_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Collections;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    /*
     * MainActivity is the starting Activity, displaying the buttons used to add the emotions
     * into the emotionHistory. It also allows the users to navigate to the EmotionHistoryActivity
     * by the Browse History button, the EmotionCountActivity by the Emotion Count button and
     * the AddCommentActivity by holding on any one of the emotion buttons.To quick add an
     * emotion, the user will click on the desired emotion button. To add a comment to the
     * emotion, the user will hold onto the button and will then be taking to AddCommentActivity.
     */

    private static final String FILENAME = "filetest.sav";
    private EmotionHistory emotionHistory;
    private static final int SUBMIT_COMMENT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emotionHistory = new EmotionHistory().loadFromFile(FILENAME, this);
        setContentView(R.layout.activity_main);

        /* Setting up the buttons used to add the emotions and navigate to different activities */
        Button joyButton = findViewById(R.id.addJoy);
        Button angerButton = findViewById(R.id.addAnger);
        Button loveButton = findViewById(R.id.addLove);
        Button sadButton = findViewById(R.id.addSad);
        Button fearButton = findViewById(R.id.addFear);
        Button surpriseButton = findViewById(R.id.addSurprise);
        Button historyButton = findViewById(R.id.historyButton);
        Button emotionCountButton = findViewById(R.id.emotionCountButton);

        /* Setting up of the listeners for each of the buttons in the activity and in the case for
         * each of the emotion buttons, click and longclick listeners
         */
        historyButton.setOnClickListener(this);
        emotionCountButton.setOnClickListener(this);
        joyButton.setOnClickListener(this);
        angerButton.setOnClickListener(this);
        loveButton.setOnClickListener(this);
        sadButton.setOnClickListener(this);
        fearButton.setOnClickListener(this);
        surpriseButton.setOnClickListener(this);
        joyButton.setOnLongClickListener(this);
        angerButton.setOnLongClickListener(this);
        loveButton.setOnLongClickListener(this);
        sadButton.setOnLongClickListener(this);
        fearButton.setOnLongClickListener(this);
        surpriseButton.setOnLongClickListener(this);
    }

    /* This method is used to retrieve emotion with optional comment from the AddCommentActivity
     * and insert it into the emotionHistory.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SUBMIT_COMMENT) {
            if (resultCode == RESULT_OK) {
                Emotion emotionAddedComment = AddCommentActivity.getEmotion(data);
                emotionHistory.addEmotion(emotionAddedComment);
                Toast.makeText(MainActivity.this, emotionAddedComment.getEmotionName() + " Added!", Toast.LENGTH_LONG).show();
                Collections.sort((List<Emotion>) emotionHistory.getEmotionHistory(), new EmotionComparator());
                emotionHistory.saveInFile(FILENAME, this, emotionHistory);

            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        emotionHistory = emotionHistory.loadFromFile(FILENAME, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        emotionHistory = emotionHistory.loadFromFile(FILENAME, this);
    }

    /* This method is the onClick method that each button uses. This method takes the appropriate
     * action when any one of the buttons is clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.historyButton:
                emotionHistory.saveInFile(FILENAME, MainActivity.this, emotionHistory);
                Intent historyIntent = new Intent(this, EmotionHistoryActivity.class);
                Toast.makeText(MainActivity.this, "History", Toast.LENGTH_LONG).show();
                startActivity(historyIntent);
                break;

            case R.id.emotionCountButton:
                emotionHistory.saveInFile(FILENAME, MainActivity.this, emotionHistory);
                Intent countIntent = new Intent(this, EmotionCountActivity.class);
                Toast.makeText(MainActivity.this, "Emotion Count", Toast.LENGTH_LONG).show();
                startActivity(countIntent);
                break;

            case R.id.addAnger:
                Anger anger = new Anger(new Date());
                emotionHistory.addEmotion(anger);
                Toast.makeText(MainActivity.this, "Anger Added!", Toast.LENGTH_LONG).show();
                break;

            case R.id.addFear:
                Fear fear = new Fear(new Date());
                emotionHistory.addEmotion(fear);
                Toast.makeText(MainActivity.this, "Fear Added!", Toast.LENGTH_LONG).show();
                break;

            case R.id.addJoy:
                Joy joy = new Joy(new Date());
                emotionHistory.addEmotion(joy);
                Toast.makeText(MainActivity.this, "Joy Added!", Toast.LENGTH_LONG).show();
                break;

            case R.id.addLove:
                Love love = new Love(new Date());
                emotionHistory.addEmotion(love);
                Toast.makeText(MainActivity.this, "Love Added!", Toast.LENGTH_LONG).show();
                break;

            case R.id.addSad:
                Sadness sad = new Sadness(new Date());
                emotionHistory.addEmotion(sad);
                Toast.makeText(MainActivity.this, "Sad Added!", Toast.LENGTH_LONG).show();
                break;

            case R.id.addSurprise:
                Surprise surprise = new Surprise(new Date());
                emotionHistory.addEmotion(surprise);
                Toast.makeText(MainActivity.this, "Surprise Added!", Toast.LENGTH_LONG).show();
                break;
        }
        Collections.sort((List<Emotion>) emotionHistory.getEmotionHistory(), new EmotionComparator());
        emotionHistory.saveInFile(FILENAME, this, emotionHistory);
    }

    /* This method is used when any on of the emotion buttons is hold to add an additional comment
     * to the emotion.
     */
    @Override
    public boolean onLongClick(View v) {
        Emotion emotion;
        switch (v.getId()) {
            case R.id.addAnger:
                emotion = new Anger(new Date());
                break;

            case R.id.addFear:
                emotion = new Fear(new Date());
                break;

            case R.id.addJoy:
                emotion = new Joy(new Date());
                break;

            case R.id.addLove:
                emotion = new Love(new Date());
                break;

            case R.id.addSad:
                emotion = new Sadness(new Date());
                break;

            case R.id.addSurprise:
                emotion = new Surprise(new Date());
                break;

            default:
                emotion = null;
                break;
        }
        Intent intent = AddCommentActivity.newAddCommentIntent(MainActivity.this, emotion);
        Toast.makeText(MainActivity.this, "Add Comment", Toast.LENGTH_LONG).show();
        startActivityForResult(intent, SUBMIT_COMMENT);
        return true;
    }

}
