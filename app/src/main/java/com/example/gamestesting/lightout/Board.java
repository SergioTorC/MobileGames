package com.example.gamestesting.lightout;

public class Board {
    // Instance variables
    private Cell[][] board; // Representation of the game board, an m * n board of cells.
    private int moves; // Current number of moves completed.

    private void createEmptyBoard(int height, int width) {
        board = new Cell[height][width]; // Creates the height*width table of Cell objects

        // Iterates through the board and initializes each cell
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
                board[i][j].setOn(false); // All cells are initially not illuminated (allows an achievable solution to occur using randomization)
            }
        }
    }

    public Board(int height, int width) {
        createEmptyBoard(height, width); // We initialize an empty board.

    }

    public Board(Board parameterBoard) {
        board = new Cell[parameterBoard.getHeight()][parameterBoard.getWidth()]; // Uses the parameter board to initialize a new one
        for (int i = 0; i < board.length; i++) { // We can then iterate through and set each cell to match the parameter
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
                board[i][j].setOn(parameterBoard.getPos(i, j).getOn()); // Copying whether the cell is illuminated.
            }
        }
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }


    public int getMoves() {
        return moves;
    }

    public int getHeight() {
        return board.length;
    }

    public int getWidth() {
        return board[0].length;
    }

    public Cell getPos(int i, int j) {
        return board[i][j];
    }

    public void click(int height, int width) {
        // If the height or width exceeds the dimensions, we throw an exception.
        if (height >= board.length || width >= board[0].length || height < 0 || width < 0) {
            throw new IllegalArgumentException();
        }
        if (height - 1 >= 0) {// Toggles the light above, if it is on the board and is active.
            board[height - 1][width].toggleLight();
        }
        if (height + 1 < board.length) { // Toggles the light below, if it is on the board and is active.
            board[height + 1][width].toggleLight();
        }
        if (width - 1 >= 0) { // Toggles the light to the left, if it is on the board and is active.
            board[height][width - 1].toggleLight();
        }
        if (width + 1 < board[0].length) { // Toggles the light to the right, if it is on the board and is active.
            board[height][width + 1].toggleLight();
        }

        board[height][width].toggleLight(); // Toggles the light that was clicked.
        moves++; // Increments the move counter, as the user has performed a move.
    }

    public boolean hasWon() {
        for (int i = 0; i < board.length; i++) { // We iterate through the board to check each cell individually.
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getOn()) { // If a cell is found that is active and off, we immediately return false, as the user has not won.
                    return false;
                }
            }
        }
        return true; // If false has not been returned by now, we return that the user won.
    }

    public void randomize() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                double probability = 0.55;
                if (Math.random() < probability) { // If that chance occurs, we click it and increment the number of moves.
                    click(i, j);
                }
            }
        }
        moves = 0; // Since the click(i,j) call increments the moves, we reset it.
    }

}
