package com.example.gamestesting.lightout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamestesting.R;

public class EasyGame extends AppCompatActivity {
    GameController game;
    ImageButton[][] buttons;
    TextView moves;
    TextView points;
    MediaPlayer mpWin;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game);

        SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        score = prefs.getInt("points", 0); // Retrieves the user's current score

        // Initializes the button array
        buttons = new ImageButton[4][4];
        buttons[0][0] = findViewById(R.id.imageButton);
        buttons[0][1] = findViewById(R.id.imageButton2);
        buttons[0][2] = findViewById(R.id.imageButton3);
        buttons[0][3] = findViewById(R.id.imageButton4);

        buttons[1][0] = findViewById(R.id.imageButton5);
        buttons[1][1] = findViewById(R.id.imageButton6);
        buttons[1][2] = findViewById(R.id.imageButton7);
        buttons[1][3] = findViewById(R.id.imageButton8);

        buttons[2][0] = findViewById(R.id.imageButton9);
        buttons[2][1] = findViewById(R.id.imageButton10);
        buttons[2][2] = findViewById(R.id.imageButton11);
        buttons[2][3] = findViewById(R.id.imageButton12);

        buttons[3][0] = findViewById(R.id.imageButton13);
        buttons[3][1] = findViewById(R.id.imageButton14);
        buttons[3][2] = findViewById(R.id.imageButton15);
        buttons[3][3] = findViewById(R.id.imageButton16);

        moves = findViewById(R.id.textView11);
        points = findViewById(R.id.textView4);
        mpWin = MediaPlayer.create(this, R.raw.tada);
        game = new GameController(buttons, moves, points, score);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            // We determine which button was selected and insert the current user there.
            // The first set is buttons on the game board, which clicks their corresponding button
            case R.id.imageButton:
                game.click(0,0);
                break;
            case R.id.imageButton2:
                game.click(0,1);
                break;
            case R.id.imageButton3:
                game.click(0,2);
                break;
            case R.id.imageButton4:
                game.click(0,3);
                break;
            case R.id.imageButton5:
                game.click(1,0);
                break;
            case R.id.imageButton6:
                game.click(1,1);
                break;
            case R.id.imageButton7:
                game.click(1,2);
                break;
            case R.id.imageButton8:
                game.click(1,3);
                break;
            case R.id.imageButton9:
                game.click(2,0);
                break;
            case R.id.imageButton10:
                game.click(2,1);
                break;
            case R.id.imageButton11:
                game.click(2,2);
                break;
            case R.id.imageButton12:
                game.click(2,3);
                break;
            case R.id.imageButton13:
                game.click(3,0);
                break;
            case R.id.imageButton14:
                game.click(3,1);
                break;
            case R.id.imageButton15:
                game.click(3,2);
                break;
            case R.id.imageButton16:
                game.click(3,3);
                break;
            case R.id.button4: // Button to create a new puzzle (giving up)
                game = new GameController(buttons, moves,  points, score);
                while (game.hasWon()){ // Ensures a winning puzzle isn't used.
                    game = new GameController(buttons, moves, points, score);
                }
                game.updateView(); // Updates the view
                break;
            case R.id.button9: // Button to retry the puzzle.
                game.retryBoard();
                game.updateView(); // Updates the view
                break;
        }

        // When a user wins the game, this code is run.
        if (game.hasWon()){
            mpWin.start(); // Plays a sound.
            SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);

            int score = prefs.getInt("points", 0); // Retrieves the user's current score
            score = score + 10 + game.getBonusPoints(); // 10 points are awarded by default for easy games, with 5 extra for beating under the minimum number of moves
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("points", score); // Replaces the score with the increased amount.
            editor.apply();

            if (game.getBonusPoints() > 0){ Toast.makeText(getApplicationContext(),"You beat this Easy level within the minimum number of moves! +15 points.",Toast.LENGTH_LONG).show(); }
            else { Toast.makeText(getApplicationContext(),"You beat this Easy level! +10 points.",Toast.LENGTH_LONG).show(); }

            this.score = score;
            game.updatePoints(score);
        }
        game.updateView();
    }
}
