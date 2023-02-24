package com.example.gamestesting.lightout;

public class Cell {
    // Instance variables
    private boolean isOn; // Indicates whether the cell is on/off in the current game.

    public Cell(boolean light){
        isOn = light;
    }

    public Cell(){
        isOn = false;
    }

    public void toggleLight(){
        // If a light is on, it gets turned off.
        // Inversely, if a light is off, it gets turned on.
        setOn(!isOn);
    }
    public boolean getOn(){
        return isOn;
    }

    public void setOn(boolean var){
        isOn = var;
    }

    public String toString(){
        String s = "(" + isOn + ")";
        return s;
    }
}
