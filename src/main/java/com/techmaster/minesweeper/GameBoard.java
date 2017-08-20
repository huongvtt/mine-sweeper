package com.techmaster.minesweeper;

import com.techmaster.minesweeper.listener.CellClickListener;
import com.techmaster.minesweeper.model.Cell;
import com.techmaster.minesweeper.model.EmptyCell;
import com.techmaster.minesweeper.model.MineCell;
import com.techmaster.minesweeper.model.NumberCell;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date: 8/12/17
 */
public class GameBoard {

    private final MineMap mineMap;
    GridPane grid;
    private Map<String, Image> loadedRes;
    private int hiddenMines;

    public GameBoard(int nRow, int nCol, float mineProb) {
        mineMap = new MineMap(nRow, nCol, mineProb);
        grid = new GridPane();
        loadedRes = new HashMap<>();
        hiddenMines = Math.round(nRow * nCol * mineProb);
        initGrid(nRow, nCol);
    }

    private void initGrid(int nRow, int nCol) {
        for (int i = 0; i < nRow; i++)
            for (int j = 0; j < nCol; j++) {
                //TODO:
                //1. Dùng hàm getImage để lấy ra Image tương ứng với ô ở vị trí (i,j)
                Image img = getImage(mineMap.getCell(i,j));
                //2. Tạo ImageView (javafx.scene.image.ImageView) từ Image lấy được
                ImageView iv = new ImageView();
                iv.setImage(img);
                //3. Tạo Button (javafx.scene.control.Button) từ ImageView
                Button btn = new Button("", iv);
                btn.setPadding(new Insets(0, 0, 0, 0));
                btn.setOnMouseClicked(new CellClickListener(mineMap.getCell(i,j), iv, this));
                //4. Thêm instance của Button vừa tạo vào vị trí (i,j) trong object 'grid'
                grid.add(btn, i, j);

            }
    }

    private Image getImage(Cell c) {
        String resName = "blank.png";

        //TODO: Thay đổi giá trị của resName tuỳ theo kiểu của 'c'
        //only for 16/8
        /*if (c instanceof NumberCell){
            resName = "number" + ((NumberCell) c).getCountMine() + ".png";
        } else if (c instanceof EmptyCell){
            resName = "exposed.png";
        } else if (c instanceof MineCell){
            resName = "flag.png";
        }
        */

        if (!(c.isDiscovered())){
            if(c.isFlagged())
                resName = "flag.png";
            else
                resName = "blank.png";
        } else
        if (c instanceof NumberCell){
            resName = "number" + ((NumberCell) c).getCountMine() + ".png";
        } else if (c instanceof EmptyCell){
            resName = "exposed.png";
        } else if (c instanceof MineCell){
            resName = "mine.png";
        }

        Image res = loadedRes.get(resName);
        if (res == null) {
            res = new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(resName));
            loadedRes.put(resName, res);
        }

        return res;
    }

    public Image loadImage(String resName){
        Image res = loadedRes.get(resName);
        if (res == null) {
            res = new Image(Thread.currentThread().getContextClassLoader().getResourceAsStream(resName));
            loadedRes.put(resName, res);
        }

        return res;
    }

    public int getHiddenMines(){
        return hiddenMines;
    }

    public void setHiddenMines(int hm){
        this.hiddenMines = hm;
    }

    public void gameOver(){

    }

    public void winGame(){

    }
}