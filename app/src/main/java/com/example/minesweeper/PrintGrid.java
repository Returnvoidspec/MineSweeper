package com.example.minesweeper;

import android.util.Log;

public class PrintGrid {

    public static void print(final int[][] grid,final int width,final int height){//little class to display the grid in the log to check if its good
        for(int x = 0;x<width;x++){
            String printed ="| ";
            for(int y = 0;y<height;y++){
                printed += String.valueOf(grid[x][y]).replace("-1","M")+" |";
            }
            Log.e("",printed);
        }
    }
}
