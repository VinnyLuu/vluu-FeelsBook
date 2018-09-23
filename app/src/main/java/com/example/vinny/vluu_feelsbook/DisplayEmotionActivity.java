package com.example.vinny.vluu_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DisplayEmotionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_emotion);
    }

    public void editEmotion(View view){
        Intent intent = new Intent(this, EditEmotionActivity.class);
        startActivity(intent);
    }
}
