package com.example.minesweeper;

import android.content.Context;
import android.util.Log;
import android.view.View;

public class GameEngine {

    private static GameEngine instance;

    public static final int BOMB_NUMBER = 20;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;


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
        int y = position/HEIGHT;

        return MineSweeperGrid[x][y];
    }

    public Cell getCellAt(int x,int y){

        return MineSweeperGrid[x][y];
    }

    public void click(int x,int y){
        if(x >= 0 && y >= 0 && x<WIDTH && y<HEIGHT && !getCellAt(x,y).isClicked()){
            getCellAt(x,y).setClicked();

            if(getCellAt(x,y).getValue() == 0){
                for(int xt = -1;xt<=1;xt++){
                    for(int yt = -1;yt<=1;yt++){
                        if(xt != yt){
                            click(x+xt,y+yt);
                        }
                    }
                }
            }
            if(getCellAt(x,y).isBomb()){
                onGameLost();
            }
        }
    }

    private void onGameLost() {
        //handle lost game
    }
}
