package com.example.vinny.vluu_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

public class EditEmotionActivity extends AppCompatActivity {
    /*
     * EditEmotionActivity is the activity that allows user to edit the selected emotion date and
     * comment. EditEmotionActivity is created when the user taps on any emotion from the emotion
     * list in EmotionHistoryActivity. EditEmotionActivity takes in an Emotion from
     * EmotionHistoryActivity which is the emotion in the emotion history selected by the user.
     *
     * When the done button is clicked, EditEmotionActivity sets the emotion's date and comment
     * to what the user has specified to and returns it back to EmotionHistoryActivity. If either
     * the user has entered a comment longer than 100 characters or has incorrectly inputted a
     * date in the wrong format, the user will be returned to EmotionHistoryActivity with a toast
     * describing the error and asking the user to retry.
     */

    private static final String EDITING_EMOTION = "com.example.vinny.vluu_feelsbook.EDITING_EMOTION";
    private static final String EXTRA_EDITED_EMOTION = "com.example.vinny.vluu_feelsbook.EXTRA_EDITED_EMOTION";
    private static final String EXTRA_OLD_EMOTION_INDEX = "com.example.vinny.vluu_feelsbook.EXTRA_EMOTION_INDEX";

    private EditText emotionCommentView;
    private TextView emotionNameView;
    private EditText emotionDateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_emotion);

        /* Getting emotion selected by user sent from EmotionHistoryActivity */
        final Emotion oldEmotion = (Emotion) getIntent().getSerializableExtra(EDITING_EMOTION);
        final Integer extraEmotionIndex = getIntent().getIntExtra(EXTRA_OLD_EMOTION_INDEX, 0);

        /* Setting up text views to display date and comment */
        emotionNameView = findViewById(R.id.emotionAddedTextView);
        emotionNameView.setText(oldEmotion.getEmotionName());
        emotionCommentView = findViewById(R.id.commentEditView);
        emotionCommentView.setText(oldEmotion.getEmotionComment());
        emotionDateView = findViewById(R.id.editDateTextView);
        emotionDateView.setText(oldEmotion.getEmotionDate());
        Button finishEdit = findViewById(R.id.submitButton);

        /* This button is clicked when the user wants to submit the edited comment and date */
        finishEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmotionComment = emotionCommentView.getText().toString();
                String date = String.valueOf(emotionDateView.getText());
                try {
                    oldEmotion.setEmotionDate(date);
                } catch (ParseException e) {
                    Toast.makeText(EditEmotionActivity.this, "Incorrect Date Format! Retry!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                try {
                    oldEmotion.setEmotionComment(newEmotionComment);
                } catch (EmotionCommentTooLong emotionCommentTooLong) {
                    Toast.makeText(EditEmotionActivity.this, "Comment Too Long! Retry!", Toast.LENGTH_LONG).show();
                    emotionCommentTooLong.printStackTrace();
                }
                Intent editedEmotion = new Intent();
                editedEmotion.putExtra(EXTRA_OLD_EMOTION_INDEX, extraEmotionIndex);
                editedEmotion.putExtra(EXTRA_EDITED_EMOTION, oldEmotion);
                setResult(RESULT_OK, editedEmotion);
                finish();
            }
        });
    }

    /* Method called when emotion needs to be extracted from extra (called in EmotionHistoryActivity) */
    public static Emotion getEditedEmotion(Intent result) {
        return (Emotion) result.getSerializableExtra(EXTRA_EDITED_EMOTION);
    }

    /* Method called when index of emotion needs to be extracted from extra (called in EmotionHistoryActivity) */
    public static int getIndex(Intent result) {
        return result.getIntExtra(EXTRA_OLD_EMOTION_INDEX, 0);
    }

    /* Method called to create a new intent to EditEmotionActivity */
    public static Intent newEditEmotionIntent(Context packageContext, Emotion emotion, int index) {
        Intent i = new Intent(packageContext, EditEmotionActivity.class);
        i.putExtra(EDITING_EMOTION, emotion);
        i.putExtra(EXTRA_OLD_EMOTION_INDEX, index);
        return i;
    }
}
