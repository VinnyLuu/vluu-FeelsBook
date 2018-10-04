package com.example.vinny.vluu_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCommentActivity extends AppCompatActivity {
    // Controller that will display a popup window.

    private static final String OLD_EMOTION_COMMENT = "com.example.vinny.vluu_feelsbook.OLD_COMMENT";
    private static final String EXTRA_NEW_COMMENT = "com.example.vinny.vluu_feelsbook.COMMENT";
    private EditText emotionBody;
    private TextView emotionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int) (dm.widthPixels * 0.8);
        int height = (int) (dm.heightPixels * 0.6);

        getWindow().setLayout(width, height);

        emotionName = findViewById(R.id.addedEmotionName);
        final Emotion emotion = (Emotion) getIntent().getSerializableExtra(OLD_EMOTION_COMMENT);
        emotionName.setText(emotion.getEmotionName());

        emotionBody = findViewById(R.id.addCommentView);
        Button commitComment = findViewById(R.id.finishComment);
        commitComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = emotionBody.getText().toString();
                try {
                    emotion.setEmotionComment(comment);
                } catch (EmotionCommentTooLong emotionCommentTooLong) {
                    emotionCommentTooLong.printStackTrace();
                    Toast.makeText(AddCommentActivity.this, "Comment Too Long! Retry.", Toast.LENGTH_LONG).show();
                    finish();
                }
                Intent addedComment = new Intent();
                addedComment.putExtra(EXTRA_NEW_COMMENT, emotion);
                setResult(RESULT_OK, addedComment);
                finish();
            }
        });

    }

    public static Emotion getEmotion(Intent result) {
        return (Emotion) result.getSerializableExtra(EXTRA_NEW_COMMENT);
    }

    public static Intent newIntent(Context packageContext, Emotion emotion) {
        Intent i = new Intent(packageContext, AddCommentActivity.class);
        i.putExtra(OLD_EMOTION_COMMENT, emotion);
        return i;
    }
}
