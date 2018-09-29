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


public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ListView emotionList;
    private EmotionHistory emotionHistory = new EmotionHistory();
    private ArrayAdapter<Emotion> adapter;

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
                String s = emotionList.getItemAtPosition(position).toString();

                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });

        joyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emotion joy = new Emotion("Joy", new Date());
                emotionHistory.addEmotion(joy);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });

        angerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emotion anger = new Emotion("Anger", new Date());
                emotionHistory.addEmotion(anger);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });

        loveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emotion love = new Emotion("Love", new Date());
                emotionHistory.addEmotion(love);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });

        sadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emotion sad = new Emotion("Sad", new Date());
                emotionHistory.addEmotion(sad);
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        });

        fearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emotion fear = new Emotion("Fear", new Date());
                emotionHistory.addEmotion(fear);
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        });

        surpriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emotion surprise = new Emotion("Surprise", new Date());
                emotionHistory.addEmotion(surprise);
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Emotion>(this, android.R.layout.simple_list_item_1 , (ArrayList<Emotion>) emotionHistory.getEmotionHistory());
        emotionList.setAdapter(adapter);
    }

    public void viewHistory(View view){
        Intent intent = new Intent(this, DisplayEmotionActivity.class);
        startActivity(intent);
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

}
