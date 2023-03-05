package com.example.gamestesting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gamestesting.game2048.ScoreManagement;

public class Help extends AppCompatActivity {
    String url;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        TextView githubText = findViewById(R.id.githubText);
        TextView twitterText = findViewById(R.id.twitterText);
        TextView instagramText = findViewById(R.id.instragramText);

        githubText.setOnClickListener(v -> {
            url = "https://github.com/storrescano";
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        twitterText.setOnClickListener(v -> {
            url = "https://www.cifpfbmoll.eu/";
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        instagramText.setOnClickListener(v -> {
            url = "https://www.cifpfbmoll.eu/";
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });


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
                intent = new Intent(Help.this, SelectorActivity.class);
                startActivity(intent);
                return true;
            case R.id.Score:
                intent = new Intent(Help.this, ScoreManagement.class);
                startActivity(intent);
                return true;
            case R.id.help:
                intent = new Intent(Help.this, Help.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
