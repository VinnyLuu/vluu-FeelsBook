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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmotionHistoryActivity extends AppCompatActivity {
    /*
     * EmotionHistoryActivity is the activity that will display the emotion history from the save
     * file. It allows for the user to browse the emotions previously inputted and select an
     * emotion to edit or delete. To edit an Emotion, the user will click on the desired emotion
     * to edit on the list view. To delete an Emotion, the user will hold on the desired
     * emotion to be deleted on the list view.
     */

    private ListView emotionList;
    private EmotionHistory emotionHistory;
    private ArrayAdapter<Emotion> adapter;
    private static final int EDIT_EMOTION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_history);
        emotionHistory = new EmotionHistory().loadFromFile(this);
        emotionList = findViewById(R.id.emotionList);

        /* On a click on the list view that displays an emotion, start new EditEmotionActivity to
         * edit the emotion.
         */
        emotionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Emotion emotion = (Emotion) emotionList.getItemAtPosition(position);
                Toast.makeText(EmotionHistoryActivity.this, "Edit Emotion", Toast.LENGTH_LONG).show();
                Intent intent = EditEmotionActivity.newEditEmotionIntent(EmotionHistoryActivity.this, emotion, emotionHistory.getIndex(emotion));
                startActivityForResult(intent, EDIT_EMOTION);

            }
        });

        /* On a long click on the list view that displays an emotion, remove the emotion from the
         * emotionHistory.
         */
        emotionList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Emotion emotion = (Emotion) emotionList.getItemAtPosition(position);
                emotionHistory.removeEmotion(emotion);
                Toast.makeText(EmotionHistoryActivity.this, "Deleted Emotion", Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
                emotionHistory.saveInFile(EmotionHistoryActivity.this, emotionHistory);
                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        emotionHistory.loadFromFile(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, (ArrayList<Emotion>) emotionHistory.getEmotionHistory());
        emotionList.setAdapter(adapter);
    }

    /* On result after editing an emotion, this method will extract the newly edited emotion and
     * set the old emotion to the new edited emotion.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_EMOTION) {
            if (resultCode == RESULT_OK) {
                Emotion newEmotion = EditEmotionActivity.getEditedEmotion(data);
                int emotionIndex = EditEmotionActivity.getIndex(data);
                emotionHistory.setAnEmotion(newEmotion, emotionIndex);
                Collections.sort((List<Emotion>) emotionHistory.getEmotionHistory(), new EmotionComparator());
                adapter.notifyDataSetChanged();
                emotionHistory.saveInFile(this, emotionHistory);
            }
        }
    }
}
