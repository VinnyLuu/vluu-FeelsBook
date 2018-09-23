package com.example.vinny.vluu_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_page);
    }

    public void viewEmotion(View view){
        Intent intent = new Intent(this, DisplayEmotionActivity.class);
        startActivity(intent);
    }
}
