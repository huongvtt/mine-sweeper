package com.techmaster.minesweeper.listener;

import com.techmaster.minesweeper.GameBoard;
import com.techmaster.minesweeper.model.Cell;
import com.techmaster.minesweeper.model.EmptyCell;
import com.techmaster.minesweeper.model.MineCell;
import com.techmaster.minesweeper.model.NumberCell;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CellClickListener implements EventHandler<MouseEvent> {
    private Cell cell;
    private ImageView imv;
    private GameBoard gb;

    public CellClickListener(Cell _cell, ImageView _imv, GameBoard _gb){
        this.cell = _cell;
        this.imv = _imv;
        this.gb = _gb;
    }

    @Override
    public void handle(MouseEvent e) {
        if (cell.isDiscovered()) {
            System.out.println("Cell discovered, do nothing");
        } else {
            MouseButton mb = e.getButton();
            if (mb == MouseButton.PRIMARY) {
                leftMouseClick();
            } else if (mb == MouseButton.SECONDARY) {
                rightMouseClick();
            } else {
                System.out.println("Clicked on " + mb);
            }
        }
    }

    private void leftMouseClick() {
        System.out.println("Left Click");
        //TODO:
        if(cell.isDiscovered() || cell.isFlagged()) return;
        //1. Đổi trạng thái 'discovered' cho ô được click
        cell.changeDiscovered();
        //2. Đổi hình ảnh hiển thị trên ô đc click
        String resName = "exposed.png";
        if (cell instanceof NumberCell){
            resName = "number" + ((NumberCell) cell).getCountMine() + ".png";
        } else if (cell instanceof EmptyCell){
            resName = "exposed.png";
        } else if (cell instanceof MineCell){
            resName = "hitmine.png";
        }
        imv.setImage(gb.loadImage(resName));

        //3. Đổi trạng thái sang GAME OVER nếu ô được click là ô có mìn
    }

    private void rightMouseClick() {
        System.out.print("Right Click - ");
        //TODO:
        //1. Hiển thị/Ẩn hình là cờ trên ô được click
        //2. Giảm/tăng số lượng qủa mìn nếu ô hiện tại là ô có mìn tuỳ theo trạng thái của ô khi click chuột phải
        int myhm = gb.getHiddenMines();

        if(cell.isDiscovered()) return;
        cell.changeFlagged();
        if (cell.isFlagged()){
            imv.setImage(gb.loadImage("flag.png"));
            if (cell instanceof MineCell) {
                myhm--;
            } else {
                myhm++;
            }
            gb.setHiddenMines(myhm);
        } else {
            imv.setImage(gb.loadImage("blank.png"));
            if (cell instanceof MineCell) {
                myhm++;
            } else {
                myhm--;
            }
            gb.setHiddenMines(myhm);
        }
        System.out.println(myhm);
    }
}