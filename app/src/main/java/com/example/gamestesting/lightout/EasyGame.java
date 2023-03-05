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
        score = prefs.getInt("points", 0); // Recupera la puntuación actual del usuario

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
            // Determinamos qué botón se seleccionó e insertamos el usuario actual allí.
            // El primer conjunto son los botones en el tablero de juego, que hace clic en su botón correspondiente
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
            case R.id.button4: // Botón para crear un nuevo rompecabezas (rendirse)
                game = new GameController(buttons, moves,  points, score);
                while (game.hasWon()){ // Garantiza que no se utilice un rompecabezas ganador.
                    game = new GameController(buttons, moves, points, score);
                }
                game.updateView(); // Actualiza la vista
                break;
            case R.id.button9: // Botón para volver a intentar el rompecabezas.
                game.retryBoard();
                game.updateView();
                break;
        }

        // Cuando un usuario gana el juego, este código se ejecuta.
        if (game.hasWon()){
            mpWin.start(); // Plays a sound.
            SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);

            int score = prefs.getInt("points", 0); // Recupera la puntuación actual del usuario
            score = score + 10 + game.getBonusPoints(); // Se otorgan 10 puntos por defecto para juegos fáciles.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("points", score); // Reemplaza la puntuación con la cantidad aumentada.
            editor.apply();

            if (game.getBonusPoints() > 0){ Toast.makeText(getApplicationContext(),"¡Has superado este nivel Fácil! +10 puntos.",Toast.LENGTH_LONG).show(); }
            else { Toast.makeText(getApplicationContext(),"¡Has superado este nivel Fácil! +10 puntos.",Toast.LENGTH_LONG).show(); }

            this.score = score;
            game.updatePoints(score);
        }
        game.updateView();
    }
}
