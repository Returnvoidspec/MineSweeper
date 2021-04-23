package com.example.minesweeper;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class GameEngine {

    private static GameEngine instance;

    public static final int BOMB_NUMBER = 20;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static int onLost = 0;


    private Context context;
    private Cell[][] MineSweeperGrid = new Cell[WIDTH][HEIGHT];


    public static GameEngine getInstance(){
        if(instance == null){
            instance = new GameEngine();
        }
        return instance;
    }

    private GameEngine(){ }

    public void createGrid(Context context){
        Log.e("GameEngine","createGrid is working");
        this.context = context;

        //create a views.grid for the game and store it
        int[][] GeneratedGrid = Generator.generate(BOMB_NUMBER,WIDTH,HEIGHT);
        PrintGrid.print(GeneratedGrid,WIDTH,HEIGHT);
        setGrid(context,GeneratedGrid);
    }

    private void setGrid(final Context context, final int[][] grid){
        for(int x = 0;x<WIDTH;x++){
            for(int y = 0;y<HEIGHT;y++){
                if(MineSweeperGrid[x][y] == null){
                    MineSweeperGrid[x][y] = new Cell(context,y*HEIGHT+x);
                }
                MineSweeperGrid[x][y].setValue(grid[x][y]);
                MineSweeperGrid[x][y].invalidate();
            }
        }
    }

    public Cell getCellAt(int position) {
        int x = position % WIDTH;
        int y = position/WIDTH;

        return MineSweeperGrid[x][y];
    }

    public Cell getCellAt(int x,int y){

        return MineSweeperGrid[x][y];
    }

    public void click(int x,int y) {
        if (onLost == 0)
        {
            if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(x, y).isClicked())
            {
                getCellAt(x, y).setClicked();

                if (getCellAt(x, y).getValue() == 0) {
                    for (int xt = -1; xt <= 1; xt++) {
                        for (int yt = -1; yt <= 1; yt++) {
                            if (xt != yt) {
                                click(x + xt, y + yt);
                            }
                        }
                    }
                }
                if (getCellAt(x, y).isBomb()) {
                    onGameLost();
                }
            }
        }
        checkEnd();
    }

    private boolean checkEnd() {
        int bombNotFound = BOMB_NUMBER;
        int notRevealed = WIDTH+HEIGHT;
        for(int x = 0;x<WIDTH; x++){
            for(int y = 0;y<HEIGHT;y++){
                if(getCellAt(x,y).isRevealed() || getCellAt(x,y).isFlaged() ){
                    notRevealed--;
                }

                if(getCellAt(x,y).isFlaged() && getCellAt(x,y).isBomb()){
                    bombNotFound--;
                }
            }
        }
        if(bombNotFound == 0 && notRevealed == 0){
            Toast.makeText(context,"Game won", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void onGameLost() {
        //handle lost game
        Toast.makeText(context,"Game lost",Toast.LENGTH_SHORT).show();

        for(int x = 0;x<WIDTH; x++){
            for(int y = 0;y<HEIGHT;y++){
                getCellAt(x,y).setRevealed();
            }
        }
        onLost = 1;
    }
}
