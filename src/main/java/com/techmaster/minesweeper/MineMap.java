package com.techmaster.minesweeper;

import com.techmaster.minesweeper.model.Cell;
import com.techmaster.minesweeper.model.EmptyCell;
import com.techmaster.minesweeper.model.MineCell;
import com.techmaster.minesweeper.model.NumberCell;

import java.util.Random;

public class MineMap {
    private Cell[][] cells;

    private int nRow;

    private int nCol;

    private float mineProb;

    public MineMap(int nRow, int nCol, float mineProb) {
        this.nRow = nRow;
        this.nCol = nCol;
        this.mineProb = mineProb;

        cells = new Cell[nRow][nCol];

        setMines();
        initCells();
    }

    public Cell getCell(int i, int j){
        return cells[i][j];
    }

    private void setMines() {
        //TODO: Tạo ngẫu nhiên các ô có mìn, với xác suất có mìn là mineProb
        int tableSize = nRow * nCol;
        int numMine = Math.round(tableSize * mineProb);

        Random rand = new Random();
        for (int i=0; i<numMine; i++){
            int myMine = rand.nextInt(tableSize);
            int c = (myMine % nCol);
            int r = (myMine / nCol);

            if (!(cells[r][c] instanceof  MineCell))
                cells[r][c] = new MineCell();
            else i--;
        }
    }

    private void initCells() {
        for (int i = 0; i < nRow; i++)
            for (int j = 0; j < nCol; j++) {
                initCell(i, j);
            }
    }

    private boolean isMineCell(int rowIdx, int colIdx){
        if(rowIdx >=0 && colIdx >=0 && rowIdx <nRow && colIdx <nCol)
            return (cells[rowIdx][colIdx] instanceof  MineCell);

        return false;
    }

    private void initCell(int rowIdx, int colIdx) {
        //TODO: Xác định xem trong trường hợp ô ở vị trí rowIdx, colIdx chưa được khởi tạo (== null)
        //thì nó phải là ô trống hay ô số, sau đó gán giá trị tương ứng vào mảng *cells*
        if(!(cells[rowIdx][colIdx] instanceof MineCell)){
            int numMine = 0;
            if(isMineCell(rowIdx-1,colIdx-1)) numMine++;
            if(isMineCell(rowIdx-1, colIdx)) numMine++;
            if(isMineCell(rowIdx-1,colIdx+1)) numMine++;
            if(isMineCell(rowIdx,colIdx-1)) numMine++;
            if(isMineCell(rowIdx,colIdx+1)) numMine++;
            if(isMineCell(rowIdx+1,colIdx-1)) numMine++;
            if(isMineCell(rowIdx+1, colIdx)) numMine++;
            if(isMineCell(rowIdx+1,colIdx+1)) numMine++;

            if (numMine == 0)
                cells[rowIdx][colIdx] = new EmptyCell();
            else
                cells[rowIdx][colIdx] = new NumberCell(numMine);
        }
    }

    private void display() {
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                System.out.print("|" + cells[i][j]);
            }
            System.out.print("|");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MineMap bg = new MineMap(20, 30, 0.15f);
        System.out.println();
        bg.display();
    }
}