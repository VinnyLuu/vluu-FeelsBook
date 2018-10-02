package com.example.vinny.vluu_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Date;

public class EditEmotionActivity extends AppCompatActivity {

    private static final String EDITING_EMOTION = "com.example.vinny.vluu_feelsbook.EDITING_EMOTION";
    private static final String EXTRA_EDITED_EMOTION = "com.example.vinny.vluu_feelsbook.EXTRA_EDITED_EMOTION";
    private static final String EXTRA_OLD_EMOTION_INDEX = "com.example.vinny.vluu_feelsbook.EXTRA_EMOTION_INDEX";

    private EditText emotionCommentView;
    private TextView emotionNameView;
    private EditText emotionDateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_emotion);

        final Emotion oldEmotion = (Emotion) getIntent().getSerializableExtra(EDITING_EMOTION);
        final Integer extraEmotionIndex = getIntent().getIntExtra(EXTRA_OLD_EMOTION_INDEX, 0);

        emotionNameView = findViewById(R.id.emotionAddedTextView);
        emotionNameView.setText(oldEmotion.getEmotionName());
        emotionCommentView = findViewById(R.id.commentEditView);
        emotionCommentView.setText(oldEmotion.getEmotionComment());
        emotionDateView = findViewById(R.id.editDateTextView);
        emotionDateView.setText(oldEmotion.getEmotionDate().toString());

        Button finishEdit = findViewById(R.id.submitButton);
        finishEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmotionComment = emotionCommentView.getText().toString();
                // TODO DATE
                Date date = new Date();
                Emotion newEmotion = null;
                try {
                    newEmotion = new Emotion(oldEmotion.getEmotionName(), date, newEmotionComment);
                } catch (EmotionCommentTooLong emotionCommentTooLong) {
                    finish();
                }
                Intent editedEmotion = new Intent();
                editedEmotion.putExtra(EXTRA_OLD_EMOTION_INDEX, extraEmotionIndex);
                editedEmotion.putExtra(EXTRA_EDITED_EMOTION, newEmotion);
                setResult(RESULT_OK, editedEmotion);
                finish();
            }
        });
    }

    public static Emotion getEditedEmotion(Intent result) {
        return (Emotion) result.getSerializableExtra(EXTRA_EDITED_EMOTION);
   }

    public static int getIndex(Intent result) {
        return result.getIntExtra(EXTRA_OLD_EMOTION_INDEX, 0);
    }

    public static Intent newIntent(Context packageContext, Emotion emotion, int index) {
        Intent i = new Intent(packageContext, EditEmotionActivity.class);
        i.putExtra(EDITING_EMOTION, emotion);
        i.putExtra(EXTRA_OLD_EMOTION_INDEX, index);
        return i;
    }
}
