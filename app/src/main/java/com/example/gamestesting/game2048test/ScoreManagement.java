package com.example.gamestesting.game2048test;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamestesting.R;
import com.example.gamestesting.game2048test.adapter.RecyclerAdapter;
import com.example.gamestesting.game2048test.dbScores.ScoreDbHelper;

import java.util.ArrayList;
import java.util.List;

public class ScoreManagement  extends AppCompatActivity {

    private RecyclerView rvList;
    private RecyclerAdapter adapter;
    public List<Score> scores;


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_manage);

        Spinner searchOptions = findViewById(R.id.spinner_search);
        Button searchByName = findViewById(R.id.search_name);
        Button searchByScore = findViewById(R.id.search_score);
        EditText name = findViewById(R.id.name_to_search);
        EditText score = findViewById(R.id.score_to_search);
        Button byName = findViewById(R.id.order_by_name);
        Button byScore = findViewById(R.id.order_by_score);
        Button byTime = findViewById(R.id.order_by_time);
        Button update = findViewById(R.id.update_button);
        Button delete = findViewById(R.id.delete_button);

        List<String> options = new ArrayList<>();
        options.add("=");
        options.add("<");
        options.add(">");



        ArrayAdapter<String> optionsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        optionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchOptions.setAdapter(optionsAdapter);

        ScoreDbHelper scoreDbHelper = new ScoreDbHelper(this,"scoreDb",null,1);
        SQLiteDatabase db = scoreDbHelper.getReadableDatabase();
        db.close();
        scoreDbHelper.close();

        initViews();
        initValues();



        searchByName.setOnClickListener(v -> {
            if (!name.getText().toString().equals("")){
                List<Score> scoreList = getScoreByName(name.getText().toString());
                adapter = new RecyclerAdapter(scoreList);
                rvList.setAdapter(adapter);
            }else {
                initValues();
            }
        });

        searchByScore.setOnClickListener(v -> {
            if (!score.getText().toString().equals("")){
                List<Score> scoreList = getScoreByScore(score.getText().toString(),searchOptions.getSelectedItem().toString());
                adapter = new RecyclerAdapter(scoreList);
                rvList.setAdapter(adapter);
            }else {
                initValues();
            }

        });

        byName.setOnClickListener(v -> {
            List<Score> scoreList = getScoreOrderBy("name");
            adapter = new RecyclerAdapter(scoreList);
            rvList.setAdapter(adapter);
        });

        byScore.setOnClickListener(v -> {
            List<Score> scoreList = getScoreOrderBy("score");
            adapter = new RecyclerAdapter(scoreList);
            rvList.setAdapter(adapter);
        });

        byTime.setOnClickListener(v -> {
            List<Score> scoreList = getScoreOrderBy("time");
            adapter = new RecyclerAdapter(scoreList);
            rvList.setAdapter(adapter);
        });

        update.setOnClickListener(v -> updateDialog());

        delete.setOnClickListener(v -> deleteDialog());

    }

    private void initViews(){
        rvList = findViewById(R.id.list_scores);
    }

    private void initValues(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvList.setLayoutManager(manager);

        List<Score> scoreList = getAllScores();
        adapter = new RecyclerAdapter(scoreList);
        rvList.setAdapter(adapter);

    }


    private List<Score> getAllScores(){
        List<Score> scores = new ArrayList<>();
        ScoreDbHelper scoreDbHelper = new ScoreDbHelper(this,"scoreDb",null,1);
        SQLiteDatabase db = scoreDbHelper.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM scores",null);
        if (cursor.moveToFirst()){
            do {
                Score score = new Score(cursor.getInt(0),cursor.getString(1), cursor.getInt(2),cursor.getString(3));
                scores.add(score);
            } while (cursor.moveToNext());
        }
        db.close();
        scoreDbHelper.close();
        return scores;

    }


    private List<Score> getScoreByName(String name){
        List<Score> scores = new ArrayList<>();
        ScoreDbHelper scoreDbHelper = new ScoreDbHelper(this,"scoreDb",null,1);
        SQLiteDatabase db = scoreDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM scores WHERE name = " + "'"+ name +"'"   ,null);
        if (cursor.moveToFirst()){
            do {
                Score score = new Score(cursor.getInt(0),cursor.getString(1), cursor.getInt(2),cursor.getString(3));
                scores.add(score);
            } while (cursor.moveToNext());
        }
        db.close();
        scoreDbHelper.close();
        return scores;

    }

    private List<Score> getScoreByScore(String scoreSearch, String rationalOp){
        List<Score> scores = new ArrayList<>();
        ScoreDbHelper scoreDbHelper = new ScoreDbHelper(this,"scoreDb",null,1);
        SQLiteDatabase db = scoreDbHelper.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM scores WHERE score "+ rationalOp + " '"+ scoreSearch +"'"   ,null);
        if (cursor.moveToFirst()){
            do {
                Score score = new Score(cursor.getInt(0),cursor.getString(1), cursor.getInt(2),cursor.getString(3));
                scores.add(score);
            } while (cursor.moveToNext());
        }
        db.close();
        scoreDbHelper.close();
        return scores;

    }


    @SuppressLint("Recycle")
    private List<Score> getScoreOrderBy(String order){
        List<Score> scores = new ArrayList<>();
        ScoreDbHelper scoreDbHelper = new ScoreDbHelper(this,"scoreDb",null,1);
        SQLiteDatabase db = scoreDbHelper.getReadableDatabase();
        Cursor cursor;
        if (order.equals("name")){
             cursor = db.rawQuery("SELECT * FROM scores ORDER BY "+ order + " ASC" ,null);
        }else {
           cursor = db.rawQuery("SELECT * FROM scores ORDER BY "+ order + " DESC" ,null);
        }
        if (cursor.moveToFirst()){
            do {
                Score score = new Score(cursor.getInt(0),cursor.getString(1), cursor.getInt(2),cursor.getString(3));
                scores.add(score);
            } while (cursor.moveToNext());
        }
        db.close();
        scoreDbHelper.close();
        return scores;
    }


    void updateDialog() {
        final Dialog dialog = new Dialog(ScoreManagement.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.update_dialog);

        EditText id = dialog.findViewById(R.id.id_search);
        EditText name = dialog.findViewById(R.id.new_name);
        EditText score = dialog.findViewById(R.id.new_score);
        EditText time = dialog.findViewById(R.id.new_time);

        Button update = dialog.findViewById(R.id.update_button_dialog);
        update.setOnClickListener(v -> {
            ScoreDbHelper scoreDbHelper = new ScoreDbHelper(v.getContext(),"scoreDb",null,1);
            SQLiteDatabase db = scoreDbHelper.getReadableDatabase();

            String [] param = new String[]{name.getText().toString(),score.getText().toString(),time.getText().toString()};

            db.execSQL("UPDATE scores SET name = ?, score = ?, time = ?  WHERE _id = "+"'"+ id.getText().toString() +"'" ,param);

            db.close();
            scoreDbHelper.close();
            initValues();
            dialog.dismiss();
        });

        dialog.setCancelable(true);
        dialog.show();
    }

    void deleteDialog() {
        final Dialog dialog = new Dialog(ScoreManagement.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.delete_dialog);

        EditText id = dialog.findViewById(R.id.id_delete);

        Button delete = dialog.findViewById(R.id.delete_button_dialog);
        delete.setOnClickListener(v -> {
            ScoreDbHelper scoreDbHelper = new ScoreDbHelper(v.getContext(),"scoreDb",null,1);
            SQLiteDatabase db = scoreDbHelper.getReadableDatabase();


            db.execSQL("DELETE FROM scores WHERE _id = " + "'"+ id.getText().toString() + "'");

            db.close();
            scoreDbHelper.close();
            initValues();
            dialog.dismiss();
        });

        dialog.setCancelable(true);
        dialog.show();
    }

}
