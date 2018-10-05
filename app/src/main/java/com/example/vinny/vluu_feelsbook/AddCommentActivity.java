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
    /*
     * This activity allows the user to add an additional comment before adding the comment.
     * This activity starts when the user holds on any one of the emotion buttons in MainActivity.
     * From MainActivity, an Emotion that corresponds to the one that the user selected is passed
     * to this activity. AddCommentActivity then takes the string that the user has entered as the
     * desired comment and sets the emotion's comment to it.The activity is formatted as a popup
     * window, allowing the user to leave the activity by tapping outside of the window.
     *
     * Returns emotion back to MainActivity when the done button is clicked. If the comment added
     * to the emotion was too long, the user will be returned to MainActivity without the
     * emotion being added, with a toast indicating the user that the emotion comment was too long
     * and that the user will need to retry.
     */

    private static final String OLD_EMOTION_COMMENT = "com.example.vinny.vluu_feelsbook.OLD_COMMENT";
    private static final String EXTRA_NEW_COMMENT = "com.example.vinny.vluu_feelsbook.COMMENT";
    private EditText emotionBody;
    private TextView emotionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        /* Setting up the view to be a popup window. */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int) (dm.widthPixels * 0.8);
        int height = (int) (dm.heightPixels * 0.6);

        getWindow().setLayout(width, height);

        /* Display selected emotion name */
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

    /* Method called when emotion needs to be extracted from extra (called in MainActivity) */
    public static Emotion getEmotion(Intent result) {
        return (Emotion) result.getSerializableExtra(EXTRA_NEW_COMMENT);
    }

    /* Method called to create a new intent to AddCommentActivity */
    public static Intent newAddCommentIntent(Context packageContext, Emotion emotion) {
        Intent i = new Intent(packageContext, AddCommentActivity.class);
        i.putExtra(OLD_EMOTION_COMMENT, emotion);
        return i;
    }
}
