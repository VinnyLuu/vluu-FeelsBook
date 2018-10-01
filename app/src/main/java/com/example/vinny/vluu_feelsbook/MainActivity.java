package com.example.vinny.vluu_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private static final String FILENAME = "file.sav";
    private ListView emotionList;
    private EmotionHistory emotionHistory = new EmotionHistory();
    private ArrayAdapter<Emotion> adapter;
    private static final int SUBMIT_COMMENT = 0;
    private Emotion emotionAddedComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emotionList = (ListView) findViewById(R.id.emotionList);
        Button joyButton = (Button) findViewById(R.id.addJoy);
        Button angerButton = (Button) findViewById(R.id.addAnger);
        Button loveButton = (Button) findViewById(R.id.addLove);
        Button sadButton = (Button) findViewById(R.id.addSad);
        Button fearButton = (Button) findViewById(R.id.addFear);
        Button surpriseButton = (Button) findViewById(R.id.addSurprise);

        emotionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Emotion emotion = (Emotion) emotionList.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, emotion.getEmotionName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, EditEmotionActivity.class);
                startActivity(intent);

            }
        });

        emotionList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Emotion emotion = (Emotion) emotionList.getItemAtPosition(position);
                emotionHistory.removeEmotion(emotion);
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
        // Check which request we're responding to
        if (requestCode == SUBMIT_COMMENT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                emotionAddedComment = AddComment.getEmotion(data);
                emotionHistory.addEmotion(emotionAddedComment);
                adapter.notifyDataSetChanged();
                saveInFile();
                // Do something with the contact here (bigger example below)
            }
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Emotion>(this, android.R.layout.simple_list_item_1 , (ArrayList<Emotion>) emotionHistory.getEmotionHistory());
        emotionList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();
            // Line below tries to make an instance of the tweet class. make the tweet class not abstract so that it can actually make an instance
            Type emotionHistoryType = new TypeToken<EmotionHistory>(){}.getType();
            emotionHistory = gson.fromJson(reader, emotionHistoryType);
            // This reads everything from the Json and puts it into the arraylist of tweets

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            emotionHistory = new EmotionHistory();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void saveInFile() {
        try {
            // FILE OUTPUTSTREAM makes a byte stream
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            // Char stream
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            // Buffer stream
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(emotionHistory, writer);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addAnger:
                Emotion anger = new Emotion("Anger", new Date());
                emotionHistory.addEmotion(anger);
                adapter.notifyDataSetChanged();
                saveInFile();
                break;

            case R.id.addFear:
                Emotion fear = new Emotion("Fear", new Date());
                emotionHistory.addEmotion(fear);
                adapter.notifyDataSetChanged();
                saveInFile();
                break;

            case R.id.addJoy:
                Emotion joy = new Emotion("Joy", new Date());
                emotionHistory.addEmotion(joy);
                adapter.notifyDataSetChanged();
                saveInFile();
                break;

            case R.id.addLove:
                Emotion love = new Emotion("Love", new Date());
                emotionHistory.addEmotion(love);
                adapter.notifyDataSetChanged();
                saveInFile();
                break;

            case R.id.addSad:
                Emotion sad = new Emotion("Sadness", new Date());
                emotionHistory.addEmotion(sad);
                adapter.notifyDataSetChanged();
                saveInFile();
                break;

            case R.id.addSurprise:
                Emotion surprise = new Emotion("Surprise", new Date());
                emotionHistory.addEmotion(surprise);
                adapter.notifyDataSetChanged();
                saveInFile();
                break;
        }


    }

    @Override
    public boolean onLongClick(View v) {
        String emotionName;
        switch (v.getId()) {
            case R.id.addAnger:
                emotionName = "Anger";
                break;

            case R.id.addFear:
                emotionName = "Fear";
                break;

            case R.id.addJoy:
                emotionName = "Joy";
                break;

            case R.id.addLove:
                emotionName = "Love";
                break;

            case R.id.addSad:
                emotionName = "Sadness";
                break;

            case R.id.addSurprise:
                emotionName = "Surprise";
                break;

            default:
                emotionName = " ";
                break;
        }
        Intent intent = AddComment.newIntent(MainActivity.this, emotionName);
        startActivityForResult(intent, SUBMIT_COMMENT);
        return true;
    }
}
