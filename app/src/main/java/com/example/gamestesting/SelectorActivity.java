package com.example.gamestesting;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gamestesting.game2048.ScoreManagement;
import com.example.gamestesting.game2048.SplashScreen;
import com.example.gamestesting.lightout.StartLightsOut;

public class SelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectgame);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.Select:
                intent = new Intent(SelectorActivity.this, SelectorActivity.class);
                startActivity(intent);
                return true;
            case R.id.Score:
                intent = new Intent(SelectorActivity.this, ScoreManagement.class);
                startActivity(intent);
                return true;
            case R.id.help:
                intent = new Intent(SelectorActivity.this, Help.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @SuppressLint("NonConstantResourceId")
    public void changeActivity(View view) {
        Intent intent;
        System.err.println(view.getId());
        switch (view.getId()){
            case R.id.imageButton3:
                intent = new Intent(SelectorActivity.this, StartLightsOut.class);
                startActivity(intent);
                break;
            case R.id.imageButton4:
                intent = new Intent(SelectorActivity.this, SplashScreen.class);
                startActivity(intent);
                break;
        }
    }
}