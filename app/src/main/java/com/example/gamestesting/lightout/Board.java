package com.example.gamestesting.lightout;

public class Board {
    private Cell[][] board;
    private int moves;

    private void createEmptyBoard(int height, int width) {
        board = new Cell[height][width]; // Crea la tabla heigth*width de los objetos Celda

        // Itera a través del tablero e inicializa cada celda
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
                board[i][j].setOn(false); // Todas las celdas inicialmente no están iluminadas (permite que ocurra una solución factible usando la aleatorización)
            }
        }
    }

    public Board(int height, int width) {
        createEmptyBoard(height, width); // Iniciamos un tablero vacio.

    }

    public Board(Board parameterBoard) {
        board = new Cell[parameterBoard.getHeight()][parameterBoard.getWidth()]; // Utiliza el tablero de parámetros para inicializar uno nuevo
        for (int i = 0; i < board.length; i++) { // Luego podemos iterar y configurar cada celda para que coincida con el parámetro
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
                board[i][j].setOn(parameterBoard.getPos(i, j).getOn()); // Copiando si la celda está iluminada.
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
        // Si la altura o el ancho exceden las dimensiones, lanzamos una excepción.
        if (height >= board.length || width >= board[0].length || height < 0 || width < 0) {
            throw new IllegalArgumentException();
        }
        if (height - 1 >= 0) {// Alterna la luz de arriba, si está en el tablero y está activa.
            board[height - 1][width].toggleLight();
        }
        if (height + 1 < board.length) { // Alterna la luz de abajo, si está en el tablero y está activa.
            board[height + 1][width].toggleLight();
        }
        if (width - 1 >= 0) { // Cambia la luz a la izquierda, si está en el tablero y está activa.
            board[height][width - 1].toggleLight();
        }
        if (width + 1 < board[0].length) { // Cambia la luz a la derecha, si está en el tablero y está activa.
            board[height][width + 1].toggleLight();
        }

        board[height][width].toggleLight(); // Cambia la luz en la que se hizo clic.
        moves++; // Incrementa el contador de movimientos, ya que el usuario ha realizado un movimiento.
    }

    public boolean hasWon() {
        for (int i = 0; i < board.length; i++) { // Iteramos a través del tablero para verificar cada celda individualmente.
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getOn()) { // Si se encuentra una celda que está activa y apagada, inmediatamente devolvemos falso, ya que el usuario no ha ganado.
                    return false;
                }
            }
        }
        return true; // Si no se ha devuelto falso hasta ahora, devolvemos que el usuario ganó.
    }

    public void randomize() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                double probability = 0.55;
                if (Math.random() < probability) { // Si se presenta esa posibilidad, hacemos clic en él e incrementamos el número de movimientos.
                    click(i, j);
                }
            }
        }
        moves = 0; // Dado que la llamada click(i,j) incrementa los movimientos, la reiniciamos.
    }

}
