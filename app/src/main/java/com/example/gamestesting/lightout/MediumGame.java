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

public class MediumGame extends AppCompatActivity {
    GameController game;
    ImageButton[][] buttons;
    TextView moves;
    MediaPlayer mpWin;
    TextView points;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_game);

        SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        score = prefs.getInt("points", 0); // Retrieves the user's current score
        
        // Initializes the button array
        buttons = new ImageButton[5][5];
        buttons[0][0] = findViewById(R.id.imageButton17);
        buttons[0][1] = findViewById(R.id.imageButton18);
        buttons[0][2] = findViewById(R.id.imageButton19);
        buttons[0][3] = findViewById(R.id.imageButton20);
        buttons[0][4] = findViewById(R.id.imageButton21);

        buttons[1][0] = findViewById(R.id.imageButton22);
        buttons[1][1] = findViewById(R.id.imageButton23);
        buttons[1][2] = findViewById(R.id.imageButton24);
        buttons[1][3] = findViewById(R.id.imageButton25);
        buttons[1][4] = findViewById(R.id.imageButton26);

        buttons[2][0] = findViewById(R.id.imageButton27);
        buttons[2][1] = findViewById(R.id.imageButton28);
        buttons[2][2] = findViewById(R.id.imageButton29);
        buttons[2][3] = findViewById(R.id.imageButton30);
        buttons[2][4] = findViewById(R.id.imageButton31);

        buttons[3][0] = findViewById(R.id.imageButton32);
        buttons[3][1] = findViewById(R.id.imageButton33);
        buttons[3][2] = findViewById(R.id.imageButton34);
        buttons[3][3] = findViewById(R.id.imageButton35);
        buttons[3][4] = findViewById(R.id.imageButton36);

        buttons[4][0] = findViewById(R.id.imageButton37);
        buttons[4][1] = findViewById(R.id.imageButton38);
        buttons[4][2] = findViewById(R.id.imageButton39);
        buttons[4][3] = findViewById(R.id.imageButton40);
        buttons[4][4] = findViewById(R.id.imageButton41);

        moves = findViewById(R.id.textView11);
        points = findViewById(R.id.textView8);
        mpWin = MediaPlayer.create(this, R.raw.tada);
        game = new GameController(buttons, moves, points, score);
    }


    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            // We determine which button was selected and insert the current user there.
            // The first set is buttons on the game board, which clicks their corresponding button
            case R.id.imageButton17:
                game.click(0, 0);
                break;
            case R.id.imageButton18:
                game.click(0, 1);
                break;
            case R.id.imageButton19:
                game.click(0, 2);
                break;
            case R.id.imageButton20:
                game.click(0, 3);
                break;
            case R.id.imageButton21:
                game.click(0, 4);
                break;
            case R.id.imageButton22:
                game.click(1, 0);
                break;
            case R.id.imageButton23:
                game.click(1, 1);
                break;
            case R.id.imageButton24:
                game.click(1, 2);
                break;
            case R.id.imageButton25:
                game.click(1, 3);
                break;
            case R.id.imageButton26:
                game.click(1, 4);
                break;
            case R.id.imageButton27:
                game.click(2, 0);
                break;
            case R.id.imageButton28:
                game.click(2, 1);
                break;
            case R.id.imageButton29:
                game.click(2, 2);
                break;
            case R.id.imageButton30:
                game.click(2, 3);
                break;
            case R.id.imageButton31:
                game.click(2, 4);
                break;
            case R.id.imageButton32:
                game.click(3, 0);
                break;
            case R.id.imageButton33:
                game.click(3, 1);
                break;
            case R.id.imageButton34:
                game.click(3, 2);
                break;
            case R.id.imageButton35:
                game.click(3, 3);
                break;
            case R.id.imageButton36:
                game.click(3, 4);
                break;
            case R.id.imageButton37:
                game.click(4, 0);
                break;
            case R.id.imageButton38:
                game.click(4, 1);
                break;
            case R.id.imageButton39:
                game.click(4, 2);
                break;
            case R.id.imageButton40:
                game.click(4, 3);
                break;
            case R.id.imageButton41:
                game.click(4, 4);
                break;
            case R.id.button6: // Button to create a new puzzle (giving up)
                game = new GameController(buttons, moves, points, score);
                while (game.hasWon()){ // Ensures a winning puzzle isn't used
                    game = new GameController(buttons, moves, points, score);
                }
                game.updateView(); // Updates the view
                break;
            case R.id.button10: // Retries the game
                game.retryBoard();
                game.updateView();
                break;
        }
        if (game.hasWon()){ // Occurs if a game is won
            mpWin.start(); // Plays a sound.
            SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);

            int score = prefs.getInt("points", 0); // Retrieves the user's current score
            score = score + 25 + game.getBonusPoints(); // 25 points are awarded by default, with 5 extra for beating under the minimum number of moves
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("points", score); // Replaces the score with the increased amount.
            editor.apply();

            // Displays a message based on the user's moves
            if (game.getBonusPoints() > 0){ Toast.makeText(getApplicationContext(),"You beat this Medium level within the minimum number of moves! +30 points.",Toast.LENGTH_LONG).show(); }
            else { Toast.makeText(getApplicationContext(),"You beat this Medium level! +25 points.",Toast.LENGTH_LONG).show(); }

            this.score = score;
            game.updatePoints(score);
        }
        game.updateView();
    }
}
