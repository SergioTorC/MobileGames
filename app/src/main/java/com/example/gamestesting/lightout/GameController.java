package com.example.gamestesting.lightout;

import android.annotation.SuppressLint;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gamestesting.R;

public class GameController {
    // Instance variables
    Board board; // Representation of the board.
    Board copyOfBoard; // An identical copy of the original version of the board.
    Cell[][] cells; // Contains the cells appearing on the board (to display them)
    ImageButton[][] buttons; // Table of the buttons used in the game.
    TextView moves; // Display of the number of moves completed
    TextView points;
    int pointCount;


    public GameController(ImageButton[][] buttonsArray, TextView moves, TextView points, int pointCount){
        buttons = buttonsArray; // Initializes the buttons array.
        board = new Board(buttons.length, buttons[0].length); // Creates a board object of the desired size (matching the buttons table)
        board.randomize();
        copyOfBoard = new Board(board); // Creates a copy of the board in case the user wishes to reset.

        this.moves = moves; // Sets the location to display the number of moves.
        this.points = points;
        this.pointCount = pointCount;
        updateView(); // Updates the view so the cells match the board.
    }


    @SuppressLint("SetTextI18n")
    public void updateView(){
        for (int i = 0; i < buttons.length; i++){
            for (int j = 0; j < buttons[i].length; j++) {
                // If the cells are on and active
                if (board.getPos(i,j).getOn()){
                    buttons[i][j].setImageResource(R.drawable.lighton2); // Image of the button being on is displayed
                }
                // If the cells are off and active
                else if (!board.getPos(i,j).getOn()){
                    buttons[i][j].setImageResource(R.drawable.lightoff2); // Image of the button being off is displayed
                }
            }
        }
        moves.setText(Integer.toString(board.getMoves())); // Displays the current number of moves
        points.setText(Integer.toString(pointCount));
    }

    public void updatePoints(int newPoints){
        this.pointCount = newPoints;
    }

    public void click(int i, int j){
        board.click(i,j); // Clicks the position on the board
        updateView(); // Updates the view to reflect the change.
    }

    public int getMoves(){
        return board.getMoves();
    }

    public void retryBoard(){
        board = copyOfBoard;
        copyOfBoard = new Board(copyOfBoard); // Uses the copy constructor to create a new board.
    }

    public void setMoves(int moves){
        board.setMoves(moves);
    }

    public boolean hasWon(){ return (board.hasWon()); }

    public String getWinMessage(){
            return "Lo has conseguido!";
    }

    public int getBonusPoints(){
        return 2;
    }
}
