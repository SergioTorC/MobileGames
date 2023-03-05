package com.example.gamestesting.lightout;

public class Cell {
    private boolean isOn;

    public Cell(boolean light){
        isOn = light;
    }

    public Cell(){
        isOn = false;
    }

    public void toggleLight(){
        // Si una luz está encendida, se apaga.
        // A la inversa, si una luz está apagada, se enciende.
        setOn(!isOn);
    }
    public boolean getOn(){
        return isOn;
    }

    public void setOn(boolean var){
        isOn = var;
    }

    public String toString(){
        return "(" + isOn + ")";
    }
}
