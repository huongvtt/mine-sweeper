package com.techmaster.minesweeper.model;

public class Cell {

    protected boolean discovered;
    protected boolean flagged;

    public Cell(){
        this.discovered = false;
        this.flagged = false;
    }

    public boolean isFlagged(){
        return this.flagged;
    }

    public boolean isDiscovered(){
        return this.discovered;
    }

    @Override
    public String toString() {
        return " ";
    }
}
