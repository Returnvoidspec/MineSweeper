package com.example.minesweeper;

import android.content.Context;
import android.view.View;

public abstract class BaseCell extends View {

    private int value;
    private boolean isBomb;
    private boolean isRevealed;
    private boolean isClicked;
    private boolean isFlaged;
    private int x,y;
    public int position;


    public BaseCell(Context context){
        super(context);
    }

    public int getValue(){return value;}

    public void setValue(int value){
        isBomb = false;
        isRevealed = false;
        isClicked = false;
        isFlaged = false;

        if(value == -1){
            isBomb = true;
        }

        this.value = value;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void
    setRevealed() {
        isRevealed = true;
        invalidate();
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked() {

        this.isClicked = true;
        this.isRevealed = true;

        invalidate();
    }

    public boolean isFlaged() {
        return isFlaged;
    }

    public void setFlaged(boolean flaged) {
        isFlaged = flaged;
    }


    public int getYPos() {
        return y;
    }


    public int getXPos() {
        return x;
    }



    public int getPosition(){
        return position;
    }

    public void setPosition(int position){
        this.position = position;

        x = position % GameEngine.WIDTH;
        y = position / GameEngine.HEIGHT;

        invalidate();
    }
}
