package com.example.gamestesting.game2048;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gamestesting.Help;
import com.example.gamestesting.R;
import com.example.gamestesting.SelectorActivity;
import com.example.gamestesting.game2048.dbScores.ScoreDbHelper;


public class GameActivity extends AppCompatActivity implements View.OnTouchListener {

    private MatrixView matrixView;
    private ScoreBoxView actualScore;
    private ScoreBoxView bestScore;
    private SwipeListener swipeListener;

    private Chronometer chronometer;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ScoreDbHelper scoreDbHelper = new ScoreDbHelper(this,"scoreDb",null,1);
        SQLiteDatabase db = scoreDbHelper.getReadableDatabase();
        db.close();
        scoreDbHelper.close();

        TextView textViewGameTitle = findViewById(R.id.main_text);
        String first = "Join the number to get the ";
        String next = "<font color=\"#57534F\">2048!</font>";
        textViewGameTitle.setText(Html.fromHtml(first + next));

        chronometer = findViewById(R.id.chronometer);
        actualScore = findViewById(R.id.main_actual_score);
        bestScore = findViewById(R.id.main_best_score);
        bestScore.setScore(getBestScore());
        matrixView = findViewById(R.id.matrix_view);
        matrixView.setMoveListener((score, gameOver, newSquare) -> {
            if (gameOver) {
                displayGameOverDialog();
            } else {
                if (score > 0) {
                    actualScore.addScore(score);
                    if (actualScore.getScore() > bestScore.getScore()) {
                        bestScore.setScore(actualScore.getScore());
                    }
                    if (score >= 2048) {
                        displayCongratsDialog();
                    }
                }
            }
        });




        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        Button buttonNewGame = findViewById(R.id.main_button_new_game);
        buttonNewGame.setOnClickListener(v -> onNewGameClick(null));

        LinearLayout linearLayout = findViewById(R.id.main_linearLayout);
        linearLayout.setOnTouchListener(this);
        swipeListener = new SwipeListener(this, new SwipeDirection() {
            @Override
            public void onSwipeLeft() {
                matrixView.onSwipeLeft();
            }

            @Override
            public void onSwipeRight() {
                matrixView.onSwipeRight();
            }

            @Override
            public void onSwipeUp() {
                matrixView.onSwipeUp();
            }

            @Override
            public void onSwipeDown() {
                matrixView.onSwipeDown();
            }
        });



    }



    private int getBestScore(){
        ScoreDbHelper scoreDbHelper = new ScoreDbHelper(this,"scoreDb",null,1);
        SQLiteDatabase db = scoreDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM scores ORDER BY score DESC" ,null);

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            return cursor.getInt(2);
        }


        db.close();
        scoreDbHelper.close();

        return 0;
    }



    private void onNewGameClick(Score score) {
        if(score != null){
            insertScore(score);
        }
        chronometer.stop();
        matrixView.reset();
        actualScore.resetScore();
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

    }

   void displayGameOverDialog() {
        final Dialog dialog = new Dialog(GameActivity.this);
        String gameOverText = "Has perdido, ¿otra vez? ";
        String cancelButton = "Cancel";
        String newGameButton = "New Game";

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_dialog);
        TextView tv = dialog.findViewById(R.id.message);

        EditText name = dialog.findViewById(R.id.name_user);


        tv.setText(gameOverText);
        Button left = dialog.findViewById(R.id.game_dialog_button_left);
        left.setText(cancelButton);
        left.setOnClickListener(v -> {
            Score score = new Score(name.getText().toString(),actualScore.getScore(),chronometer.getText().toString());
            onNewGameClick(score);
            dialog.dismiss();
        });
        Button right = dialog.findViewById(R.id.game_dialog_button_right);
        right.setText(newGameButton);
        right.setOnClickListener(v -> {
            Score score = new Score(name.getText().toString(),actualScore.getScore(),chronometer.getText().toString());
            onNewGameClick(score);
           dialog.dismiss();
        });
        dialog.setCancelable(true);
        dialog.show();
    }

    private void displayCongratsDialog() {
        final Dialog dialog = new Dialog(GameActivity.this);
        String gameOverText = "Has ganado, ¿otra vez? ";
        String cancelButton = "Cancel";
        String newGameButton = "New Game";

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_dialog);
        TextView tv = dialog.findViewById(R.id.message);

        EditText name = dialog.findViewById(R.id.name_user);

        tv.setText(gameOverText);
        Button left = dialog.findViewById(R.id.game_dialog_button_left);
        left.setText(cancelButton);
        left.setOnClickListener(v -> dialog.dismiss());
        Button right = dialog.findViewById(R.id.game_dialog_button_right);
        right.setText(newGameButton);
        right.setOnClickListener(v -> {
            Score score = new Score(name.getText().toString(),actualScore.getScore(),chronometer.getText().toString());
            onNewGameClick(score);
            dialog.dismiss();
        });
        dialog.setCancelable(true);
        dialog.show();
    }


    public void insertScore(Score score){
        ScoreDbHelper scoreDbHelper = new ScoreDbHelper(this,"scoreDb",null,1);
        SQLiteDatabase db = scoreDbHelper.getReadableDatabase();

        if (db != null){
            db.execSQL("INSERT INTO scores values(null,"
                    + "'" + score.getName()+"','"+score.getScore()+"','"+score.getTime()+"')");
        }

        if (db != null) {
            db.close();
        }
        scoreDbHelper.close();

    }




    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return swipeListener.getGestureDetector().onTouchEvent(event);
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
                intent = new Intent(GameActivity.this, SelectorActivity.class);
                startActivity(intent);
                return true;
            case R.id.Score:
                intent = new Intent(GameActivity.this, ScoreManagement.class);
                startActivity(intent);
                return true;
            case R.id.help:
                intent = new Intent(GameActivity.this, Help.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
