package com.example.vinny.vluu_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private static final String FILENAME = "filetest.sav";
    private ListView emotionList;
    private TextView joyCountView;
    private TextView sadCountView;
    private TextView angerCountView;
    private TextView surpriseCountView;
    private TextView loveCountView;
    private TextView fearCountView;
    private ArrayList<TextView> countViewList = new ArrayList<>();
    private EmotionHistory emotionHistory = new EmotionHistory();
    private ArrayAdapter<Emotion> adapter;
    private static final int SUBMIT_COMMENT = 0;
    private static final int EDIT_EMOTION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emotionList = findViewById(R.id.emotionList);
        Button joyButton = findViewById(R.id.addJoy);
        Button angerButton = findViewById(R.id.addAnger);
        Button loveButton = findViewById(R.id.addLove);
        Button sadButton = findViewById(R.id.addSad);
        Button fearButton = findViewById(R.id.addFear);
        Button surpriseButton = findViewById(R.id.addSurprise);
        joyCountView = findViewById(R.id.joyCountView);
        sadCountView = findViewById(R.id.sadCountView);
        angerCountView = findViewById(R.id.angerCountView);
        surpriseCountView = findViewById(R.id.surpriseCountView);
        loveCountView = findViewById(R.id.loveCountView);
        fearCountView = findViewById(R.id.fearCountView);
        countViewList.add(joyCountView);
        countViewList.add(sadCountView);
        countViewList.add(angerCountView);
        countViewList.add(surpriseCountView);
        countViewList.add(loveCountView);
        countViewList.add(fearCountView);

        emotionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Emotion emotion = (Emotion) emotionList.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Edit Emotion", Toast.LENGTH_LONG).show();
                Intent intent = EditEmotionActivity.newIntent(MainActivity.this, emotion, emotionHistory.getIndex(emotion));
                startActivityForResult(intent, EDIT_EMOTION);

            }
        });

        emotionList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Emotion emotion = (Emotion) emotionList.getItemAtPosition(position);
                emotionHistory.removeEmotion(emotion);
                updateCount(countViewList);
                Toast.makeText(MainActivity.this, "Deleted Emotion", Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
                saveInFile();
                return true;
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SUBMIT_COMMENT) {
            if (resultCode == RESULT_OK) {
                Emotion emotionAddedComment = AddComment.getEmotion(data);
                emotionHistory.addEmotion(emotionAddedComment);
                updateCount(countViewList);
                Collections.sort((List<Emotion>) emotionHistory.getEmotionHistory(), new EmotionComparator());
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        }
        if (requestCode == EDIT_EMOTION) {
            if (resultCode == RESULT_OK) {
                Emotion newEmotion = EditEmotionActivity.getEditedEmotion(data);
                int emotionIndex = EditEmotionActivity.getIndex(data);
                emotionHistory.setAnEmotion(newEmotion, emotionIndex);
                Collections.sort((List<Emotion>) emotionHistory.getEmotionHistory(), new EmotionComparator());
                adapter.notifyDataSetChanged();
                saveInFile();
                
            }
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , (ArrayList<Emotion>) emotionHistory.getEmotionHistory());
        emotionList.setAdapter(adapter);
        updateCount(countViewList);
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateCount(countViewList);
        Collections.sort((List<Emotion>) emotionHistory.getEmotionHistory(), new EmotionComparator());
        adapter.notifyDataSetChanged();
        saveInFile();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Emotion.class, new EmotionJsonAdapter());
            Gson gson = builder.create();
            Type emotionHistoryType = new TypeToken<EmotionHistory>(){}.getType();
            emotionHistory = gson.fromJson(reader, emotionHistoryType);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void saveInFile() {
        try {
            // FILE OUTPUTSTREAM makes a byte stream
            FileOutputStream fos = openFileOutput(FILENAME, 0);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addAnger:
                Anger anger = new Anger(new Date());
                emotionHistory.addEmotion(anger);
                adapter.notifyDataSetChanged();
                saveInFile();
                break;

            case R.id.addFear:
                Fear fear = new Fear(new Date());
                emotionHistory.addEmotion(fear);
                adapter.notifyDataSetChanged();
                saveInFile();
                break;

            case R.id.addJoy:
                Joy joy = new Joy(new Date());
                emotionHistory.addEmotion(joy);
                adapter.notifyDataSetChanged();
                saveInFile();
                break;

            case R.id.addLove:
                Love love = new Love(new Date());
                emotionHistory.addEmotion(love);
                adapter.notifyDataSetChanged();
                saveInFile();
                break;

            case R.id.addSad:
                Sadness sad = new Sadness(new Date());
                emotionHistory.addEmotion(sad);
                adapter.notifyDataSetChanged();
                saveInFile();
                break;

            case R.id.addSurprise:
                Surprise surprise = new Surprise(new Date());
                emotionHistory.addEmotion(surprise);
                adapter.notifyDataSetChanged();
                saveInFile();
                break;
        }

        updateCount(countViewList);
        Collections.sort((List<Emotion>) emotionHistory.getEmotionHistory(), new EmotionComparator());
        adapter.notifyDataSetChanged();
        saveInFile();

    }

    @Override
    public boolean onLongClick(View v) {
        Emotion emotion = null;
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
        Intent intent = AddComment.newIntent(MainActivity.this, emotion);
        startActivityForResult(intent, SUBMIT_COMMENT);
        return true;
    }

    private void updateCount(ArrayList<TextView> countViewList){
        for(TextView view: countViewList) {
            if(view == angerCountView){
                angerCountView.setText(String.valueOf(emotionHistory.count(new Anger(new Date()))));
            }
            if(view == joyCountView){
                joyCountView.setText(String.valueOf(emotionHistory.count(new Joy(new Date()))));
            }
            if(view == sadCountView){
                sadCountView.setText(String.valueOf(emotionHistory.count(new Sadness(new Date()))));
            }
            if(view == surpriseCountView){
                surpriseCountView.setText(String.valueOf(emotionHistory.count(new Surprise(new Date()))));
            }
            if(view == fearCountView){
                fearCountView.setText(String.valueOf(emotionHistory.count(new Fear(new Date()))));
            }
            if(view == loveCountView){
                loveCountView.setText(String.valueOf(emotionHistory.count(new Love(new Date()))));
            }
        }
    }
}
