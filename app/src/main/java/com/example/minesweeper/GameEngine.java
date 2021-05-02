package com.example.minesweeper;

import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GameEngine {

    private static GameEngine instance;

    public static final int BOMB_NUMBER = 20;//all the parameters of the game
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static int onLost = 0;
    public static int BOMB_LEFT = 20;



    private Context context;
    private Cell[][] MineSweeperGrid = new Cell[WIDTH][HEIGHT];


    public static GameEngine getInstance(){
        if(instance == null){
            instance = new GameEngine();
        }
        return instance;
    }



    public void createGrid(Context context){//function who will call our adapter generate with the game parameter above
        Log.e("GameEngine","createGrid is working");
        this.context = context;


        int[][] GeneratedGrid = Generator.generate(BOMB_NUMBER,WIDTH,HEIGHT);//create a views.grid for the game and store it
        PrintGrid.print(GeneratedGrid,WIDTH,HEIGHT);
        setGrid(context,GeneratedGrid);
    }

    private void setGrid(final Context context, final int[][] grid){
        for(int x = 0;x<WIDTH;x++){
            for(int y = 0;y<HEIGHT;y++){
                if(MineSweeperGrid[x][y] == null){
                    MineSweeperGrid[x][y] = new Cell(context,y*HEIGHT+x);
                }
                MineSweeperGrid[x][y].setValue(grid[x][y]);//take each value generate like bomb or number and put it in
                MineSweeperGrid[x][y].invalidate();
            }
        }
    }

    public Cell getCellAt(int position) {//to get a cell at a position in the grid
        int x = position % WIDTH;
        int y = position/WIDTH;

        return MineSweeperGrid[x][y];
    }

    public Cell getCellAt(int x,int y){//same function

        return MineSweeperGrid[x][y];
    }

    public void click(int x,int y) {//will set different state of the cell in the cell class to display or not new picture in the class cell
        if (onLost == 0)
        {
            if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(x, y).isClicked() && MainActivity.onmark == 0)
            {
                getCellAt(x, y).setClicked();

                if (getCellAt(x, y).getValue() == 0) {//all button are revealed if there is no bomb near by
                    for (int xt = -1; xt <= 1; xt++) {
                        for (int yt = -1; yt <= 1; yt++) {
                            if (xt != yt) {
                                click(x + xt, y + yt);
                            }
                        }
                    }
                }
                if (getCellAt(x, y).isBomb()) {//if a bomb is cliked on we call the function to end the game
                    onGameLost();
                }
            }
            //the next two condition are present to flag or not the cell depend on each mode we are
            else if(x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getCellAt(x, y).isClicked() && MainActivity.onmark == 1){
                getCellAt(x, y).setClicked();
                getCellAt(x, y).setFlaged();
                if(getCellAt(x,y).getValue() == -1){
                    BOMB_LEFT --;
                    System.out.println(BOMB_LEFT);
                }

            }
            //when a cell is already flaged we set up this Flaged and clicked bool to reset this cell
            else if(x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && getCellAt(x, y).isFlaged() && MainActivity.onmark == 1){
                getCellAt(x, y).setClicked();
                getCellAt(x, y).isFlaged = false;
                getCellAt(x, y).isClicked = false;
            }

        }
        checkEnd();
    }

    private boolean checkEnd() {//function to check if we win or loose
        int bombNotFound = BOMB_NUMBER;
        int notRevealed = WIDTH+HEIGHT;
        for(int x = 0;x<WIDTH; x++){//we check all cell of the grid
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

    private void onGameLost() {//function when a bomb is revealed
        //handle lost game
        Toast.makeText(context,"Game lost",Toast.LENGTH_SHORT).show();

        for(int x = 0;x<WIDTH; x++){//we display all the bomb left
            for(int y = 0;y<HEIGHT;y++){
                getCellAt(x,y).setRevealed();
            }
        }
        onLost = 1;//value to check if the game is loose to cancel all the next action of the player
    }
}
