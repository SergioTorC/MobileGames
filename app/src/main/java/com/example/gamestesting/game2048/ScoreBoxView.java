package com.example.gamestesting.game2048;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.gamestesting.R;

public class ScoreBoxView extends FrameLayout {

    private int score = 0;

    private final TextView textViewLabel;

    private final TextView textViewScore;

    public ScoreBoxView(Context context) {
        this(context, null, 0);
    }

    public ScoreBoxView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public int getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
        textViewScore.setText(String.valueOf(score));
    }

    public void setScore(int score) {
        this.score = score;
        textViewScore.setText(String.valueOf(score));
    }

    public ScoreBoxView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.score_box, this);
        textViewLabel = v.findViewById(R.id.score_box_text);
        textViewScore = v.findViewById(R.id.score_example);
        textViewScore.setText(String.valueOf(score));
        parseAttributes(context, attrs);
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ScoreBoxView, 0, 0);
        String label;
        try {
            label = a.getString(R.styleable.ScoreBoxView_label_text);
            textViewLabel.setText(label);
        } finally {
            a.recycle();
        }
    }

    public void addScore(int amount) {
        score += amount;
        textViewScore.setText(String.valueOf(score));
    }


}
