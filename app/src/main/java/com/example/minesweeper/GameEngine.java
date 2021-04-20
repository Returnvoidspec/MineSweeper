package com.example.minesweeper;

import android.content.Context;
import android.util.Log;

public class GameEngine {

    private static GameEngine instance;

    public static final int BOMB_NUMBER = 20;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;


    private Context context;


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

        //create a grid for the game and store it
        int[][] GeneratedGrid = Generator.generate(BOMB_NUMBER,WIDTH,HEIGHT);
        PrintGrid.print(GeneratedGrid,WIDTH,HEIGHT);
    }
}
