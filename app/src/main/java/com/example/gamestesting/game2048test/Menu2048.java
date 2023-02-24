package com.example.gamestesting.game2048test;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamestesting.R;
import com.example.gamestesting.game2048test.dbScores.ScoreDbHelper;


public class Menu2048 extends AppCompatActivity {


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        ScoreDbHelper scoreDbHelper = new ScoreDbHelper(this, "scoreDb", null, 1);
        SQLiteDatabase db = scoreDbHelper.getReadableDatabase();
        db.close();
        scoreDbHelper.close();

        Button start = findViewById(R.id.start_game_button);
        start.setOnClickListener(v -> startActivity(new Intent(Menu2048.this, GameActivity.class)));

        Button scores = findViewById(R.id.scores_button);
        scores.setOnClickListener(v -> startActivity(new Intent(Menu2048.this, ScoreManagement.class)));

    }

}
