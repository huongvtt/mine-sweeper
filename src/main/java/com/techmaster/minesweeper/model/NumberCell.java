package com.techmaster.minesweeper.model;

public class NumberCell extends Cell {
    protected int countMine;

    public NumberCell(int _countMine){
        countMine = _countMine;
    }

    public int getCountMine() {
        return countMine;
    }

    @Override
    public String toString() {
        return "" + countMine;
    }
}
