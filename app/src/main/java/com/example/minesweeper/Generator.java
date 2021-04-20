package com.example.minesweeper;

import java.util.Random;

public class Generator {

    public static int[][] generate(int bombnumber,final int width,final int height){
        Random r = new Random();//generate a random number to place the bomb

        int [][] grid = new  int[width][height];
        for(int x = 0;x<width;x++){
            grid[x] = new int[height];
        }

        while(bombnumber>0){
            int x = r.nextInt(width);
            int y = r.nextInt(height);

            if(grid[x][y] != -1){
                grid[x][y] = -1;
                bombnumber--;
            }
        }
        grid = calculateNeighbourgs(grid,width,height);
        return grid;

    }

    private static int[][] calculateNeighbourgs(int[][] grid,final int width,final int height){
        for(int x = 0;x<width;x++){
            for(int y=0;y<height;y++){
                grid[x][y] = getNeighbourNumber(grid,x,y,width,height);

            }
        }

        return grid;
    }

    private static int getNeighbourNumber(final int grid[][],final int x,final int y,final int width,final int height) {
        int count = 0;
        if(grid[x][y] == -1){
            return -1;
        }


        if(isMineAt(grid, x-1, y+1, width, height)){count++;}//top-left
        if(isMineAt(grid, x, y+1, width, height)){count++;}//top mid
        if(isMineAt(grid, x+1, y+1, width, height)){count++;}//top right
        if(isMineAt(grid, x-1, y, width, height)){count++;}//mid left
        if(isMineAt(grid, x+1, y, width, height)){count++;}//mid right
        if(isMineAt(grid, x-1, y-1, width, height)){count++;}//bottom left
        if(isMineAt(grid, x, y-1, width, height)){count++;}//bottom mid
        if(isMineAt(grid, x+1, y-1, width, height)){count++;}//bottom right

        return count;
    }

    private static boolean isMineAt(final int grid[][],final int x,final int y,final int width,final int height){
        if(x>=0 && y>=0 && x<width && y<height){
            if (grid[x][y] == -1){
                return true;
            }
        }
        return false;
    }
}
