package com.example.gamestesting.game2048test;

public class Score {

    private int id;
    private String name;
    private int score;
    private String time;



    public Score() {
    }

    public Score(String name, int score, String time) {
        this.name = name;
        this.score = score;
        this.time = time;
    }

    public Score(int id, String name, int score, String time) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getTime() {
        return time;
    }
}
