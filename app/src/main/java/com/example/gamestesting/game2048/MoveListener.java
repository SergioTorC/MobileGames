package com.example.gamestesting.game2048;


public interface MoveListener {

    public void onMove(int score, boolean gameOver, boolean newSquare);
}
