package com.example.gamestesting.lightout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamestesting.R;

public class StartLightsOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lightout);
    }
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(StartLightsOut.this, EasyGame.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(this, MediumGame.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(StartLightsOut.this, HardGame.class);
                startActivity(intent3);
                break;

        }
    }
}
