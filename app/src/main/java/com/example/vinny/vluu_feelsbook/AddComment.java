package com.example.vinny.vluu_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Date;

public class AddComment extends AppCompatActivity {

    private static final String EMOTION_COMMENT = "com.example.vinny.vluu_feelsbook.ADD_COMMENT";
    private static final String EXTRA_COMMENT = "com.example.vinny.vluu_feelsbook.COMMENT";
    private EditText emotionBody;
    private TextView emotionName;
    private String comment;
    private String emotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int) (dm.widthPixels * 0.8);
        int height = (int) (dm.heightPixels * 0.6);

        getWindow().setLayout(width, height);

        emotionName = (TextView) findViewById(R.id.addedEmotionName);
        final String emotion = getIntent().getStringExtra(EMOTION_COMMENT);
        emotionName.setText(emotion);

        emotionBody = (EditText) findViewById(R.id.addCommentView);
        Button commitComment = (Button) findViewById(R.id.finishComment);
        commitComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = emotionBody.getText().toString();
                Emotion newEmotion = null;
                try {
                    newEmotion = new Emotion(emotion, new Date(), comment);
                } catch (EmotionCommentTooLong emotionCommentTooLong) {
                    Toast.makeText(AddComment.this, "Emotion comment too long!", Toast.LENGTH_LONG).show();
                    finish();
                }
                Intent addedComment = new Intent();
                addedComment.putExtra(EXTRA_COMMENT, (Serializable) newEmotion);
                setResult(RESULT_OK, addedComment);
                finish();
            }
        });

    }

    public static Emotion getEmotion(Intent result) {
        return (Emotion) result.getSerializableExtra(EXTRA_COMMENT);
    }

    public static Intent newIntent(Context packageContext, String emotionName) {
        Intent i = new Intent(packageContext, AddComment.class);
        i.putExtra(EMOTION_COMMENT, emotionName);
        return i;
    }
}
