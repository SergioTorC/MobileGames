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

public class HardGame extends AppCompatActivity {
    GameController game;
    ImageButton[][] buttons;
    TextView moves;
    MediaPlayer mpWin;
    TextView points;
    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_game);

        SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        score = prefs.getInt("points", 0); // Retrieves the user's current score

        // Initializes the button array
        buttons = new ImageButton[7][6];
        buttons[0][0] = findViewById(R.id.imageButton42);
        buttons[0][1] = findViewById(R.id.imageButton43);
        buttons[0][2] = findViewById(R.id.imageButton44);
        buttons[0][3] = findViewById(R.id.imageButton45);
        buttons[0][4] = findViewById(R.id.imageButton46);
        buttons[0][5] = findViewById(R.id.imageButton47);

        buttons[1][0] = findViewById(R.id.imageButton48);
        buttons[1][1] = findViewById(R.id.imageButton49);
        buttons[1][2] = findViewById(R.id.imageButton50);
        buttons[1][3] = findViewById(R.id.imageButton51);
        buttons[1][4] = findViewById(R.id.imageButton52);
        buttons[1][5] = findViewById(R.id.imageButton53);

        buttons[2][0] = findViewById(R.id.imageButton54);
        buttons[2][1] = findViewById(R.id.imageButton55);
        buttons[2][2] = findViewById(R.id.imageButton56);
        buttons[2][3] = findViewById(R.id.imageButton57);
        buttons[2][4] = findViewById(R.id.imageButton58);
        buttons[2][5] = findViewById(R.id.imageButton59);

        buttons[3][0] = findViewById(R.id.imageButton60);
        buttons[3][1] = findViewById(R.id.imageButton61);
        buttons[3][2] = findViewById(R.id.imageButton62);
        buttons[3][3] = findViewById(R.id.imageButton63);
        buttons[3][4] = findViewById(R.id.imageButton64);
        buttons[3][5] = findViewById(R.id.imageButton65);

        buttons[4][0] = findViewById(R.id.imageButton66);
        buttons[4][1] = findViewById(R.id.imageButton67);
        buttons[4][2] = findViewById(R.id.imageButton68);
        buttons[4][3] = findViewById(R.id.imageButton69);
        buttons[4][4] = findViewById(R.id.imageButton70);
        buttons[4][5] = findViewById(R.id.imageButton71);

        buttons[5][0] = findViewById(R.id.imageButton72);
        buttons[5][1] = findViewById(R.id.imageButton73);
        buttons[5][2] = findViewById(R.id.imageButton74);
        buttons[5][3] = findViewById(R.id.imageButton75);
        buttons[5][4] = findViewById(R.id.imageButton76);
        buttons[5][5] = findViewById(R.id.imageButton77);

        buttons[6][0] = findViewById(R.id.imageButton78);
        buttons[6][1] = findViewById(R.id.imageButton79);
        buttons[6][2] = findViewById(R.id.imageButton80);
        buttons[6][3] = findViewById(R.id.imageButton81);
        buttons[6][4] = findViewById(R.id.imageButton82);
        buttons[6][5] = findViewById(R.id.imageButton83);

        moves = findViewById(R.id.textView11);
        points = findViewById(R.id.textView6);

        mpWin = MediaPlayer.create(this, R.raw.tada);

        game = new GameController(buttons, moves, points, score);
        game.updateView();
    }

    /**
     * Occurs when any button is clicked. Determines what button was chosen and performs a related action.
     * @param v
     */
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            // We determine which button was selected and insert the current user there.
            // The first set is buttons on the game board, which clicks their corresponding button
            case R.id.imageButton42:
                game.click(0, 0);
                break;
            case R.id.imageButton43:
                game.click(0, 1);
                break;
            case R.id.imageButton44:
                game.click(0, 2);
                break;
            case R.id.imageButton45:
                game.click(0, 3);
                break;
            case R.id.imageButton46:
                game.click(0, 4);
                break;
            case R.id.imageButton47:
                game.click(0, 5);
                break;
            case R.id.imageButton48:
                game.click(1, 0);
                break;
            case R.id.imageButton49:
                game.click(1, 1);
                break;
            case R.id.imageButton50:
                game.click(1, 2);
                break;
            case R.id.imageButton51:
                game.click(1, 3);
                break;
            case R.id.imageButton52:
                game.click(1, 4);
                break;
            case R.id.imageButton53:
                game.click(1, 5);
                break;
            case R.id.imageButton54:
                game.click(2, 0);
                break;
            case R.id.imageButton55:
                game.click(2, 1);
                break;
            case R.id.imageButton56:
                game.click(2, 2);
                break;
            case R.id.imageButton57:
                game.click(2, 3);
                break;
            case R.id.imageButton58:
                game.click(2, 4);
                break;
            case R.id.imageButton59:
                game.click(2, 5);
                break;
            case R.id.imageButton60:
                game.click(3, 0);
                break;
            case R.id.imageButton61:
                game.click(3, 1);
                break;
            case R.id.imageButton62:
                game.click(3, 2);
                break;
            case R.id.imageButton63:
                game.click(3, 3);
                break;
            case R.id.imageButton64:
                game.click(3, 4);
                break;
            case R.id.imageButton65:
                game.click(3, 5);
                break;
            case R.id.imageButton66:
                game.click(4, 0);
                break;
            case R.id.imageButton67:
                game.click(4, 1);
                break;
            case R.id.imageButton68:
                game.click(4, 2);
                break;
            case R.id.imageButton69:
                game.click(4, 3);
                break;
            case R.id.imageButton70:
                game.click(4, 4);
                break;
            case R.id.imageButton71:
                game.click(4, 5);
                break;
            case R.id.imageButton72:
                game.click(5, 0);
                break;
            case R.id.imageButton73:
                game.click(5, 1);
                break;
            case R.id.imageButton74:
                game.click(5, 2);
                break;
            case R.id.imageButton75:
                game.click(5, 3);
                break;
            case R.id.imageButton76:
                game.click(5, 4);
                break;
            case R.id.imageButton77:
                game.click(5, 5);
                break;
            case R.id.imageButton78:
                game.click(6, 0);
                break;
            case R.id.imageButton79:
                game.click(6, 1);
                break;
            case R.id.imageButton80:
                game.click(6, 2);
                break;
            case R.id.imageButton81:
                game.click(6, 3);
                break;
            case R.id.imageButton82:
                game.click(6, 4);
                break;
            case R.id.imageButton83:
                game.click(6, 5);
                break;
            case R.id.button7:// Button to create a new puzzle (giving up)
                game = new GameController(buttons, moves, points, score);
                while (game.hasWon()){ // Ensures a winning puzzle isn't used.
                    game = new GameController(buttons, moves, points, score);
                }
                game.updateView(); // Updates the view
                break;
            case R.id.button11: // Button to retry the puzzle
                game.retryBoard();
                game.updateView();
                break;
        }
        if (game.hasWon()){ // If the user wins, a message is displayed.
            mpWin.start(); // Plays a sound.
            SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
            int score = prefs.getInt("points", 0); // Retrieves the user's current score

            score = score + 50 + game.getBonusPoints(); // 50 points are awarded by default, with 5 extra for beating under the minimum number of moves
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("points", score); // Replaces the score with the increased amount.
            editor.apply();

            if (game.getBonusPoints() > 0){ Toast.makeText(getApplicationContext(),"You beat this Hard level within the minimum number of moves! +50 points.",Toast.LENGTH_LONG).show(); }
            else { Toast.makeText(getApplicationContext(),"You beat this Hard level! +55 points.",Toast.LENGTH_LONG).show(); }
            this.score = score;
            game.updatePoints(score);
        }
        game.updateView();
    }
}
