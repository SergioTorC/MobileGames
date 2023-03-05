package com.example.gamestesting.lightout;

import android.annotation.SuppressLint;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gamestesting.R;

public class GameController {
    Board board;
    Board copyOfBoard;
    Cell[][] cells;
    ImageButton[][] buttons;
    TextView moves;
    TextView points;
    int pointCount;


    public GameController(ImageButton[][] buttonsArray, TextView moves, TextView points, int pointCount){
        buttons = buttonsArray;
        board = new Board(buttons.length, buttons[0].length); // Crea un objeto de tablero del tamaño deseado (coincidiendo con la tabla de botones)
        board.randomize();
        copyOfBoard = new Board(board); // Crea una copia del tablero en caso de que el usuario desee reiniciar.

        this.moves = moves; // Establece la ubicación para mostrar el número de movimientos.
        this.points = points;
        this.pointCount = pointCount;
        updateView(); // Actualiza la vista para que las celdas coincidan con el tablero.
    }


    @SuppressLint("SetTextI18n")
    public void updateView(){
        for (int i = 0; i < buttons.length; i++){
            for (int j = 0; j < buttons[i].length; j++) {
                // Si las celdas están encendidas
                if (board.getPos(i,j).getOn()){
                    buttons[i][j].setImageResource(R.drawable.lighton2);
                }
                // Si las celdas están apagadas
                else if (!board.getPos(i,j).getOn()){
                    buttons[i][j].setImageResource(R.drawable.lightoff2);
                }
            }
        }
        moves.setText(Integer.toString(board.getMoves())); // Muestra el número actual de movimientos
        points.setText(Integer.toString(pointCount));
    }

    public void updatePoints(int newPoints){
        this.pointCount = newPoints;
    }

    public void click(int i, int j){
        board.click(i,j); // Hace clic en la posición en el tablero
        updateView(); // Actualiza la vista para reflejar el cambio.
    }

    public int getMoves(){
        return board.getMoves();
    }

    public void retryBoard(){
        board = copyOfBoard;
        copyOfBoard = new Board(copyOfBoard); // Utiliza el constructor de copia para crear un nuevo tablero.
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
