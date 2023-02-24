package com.example.gamestesting.lightout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamestesting.R;

public class StartLightOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lightout);
    }

    /**
     * Occurs when any button is clicked. Determines what button was chosen and performs a related action.
     * @param v
     */
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            // We determine which button was selected and insert the current user there.
            case R.id.button: // Starts an easy game and switches to that screen
                Intent intent = new Intent(StartLightOut.this, EasyGame.class);
                startActivity(intent);
                break;
            case R.id.button2: // Starts a medium game and switches to that screen
                Intent intent2 = new Intent(this, MediumGame.class);
                startActivity(intent2);
                break;
            case R.id.button3: // Starts a hard game and switches to that screen
                Intent intent3 = new Intent(StartLightOut.this, HardGame.class);
                startActivity(intent3);
                break;

        }
    }
}
