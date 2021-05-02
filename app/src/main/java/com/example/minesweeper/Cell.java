package com.example.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

public class Cell extends BaseCell implements View.OnClickListener {//define a class for each item of the grid the cell

    public Cell(Context context, int position){

        super(context);
        setPosition(position);
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,widthMeasureSpec);
    }

    @Override
    public void onClick(View v) {

        GameEngine.getInstance().click(getXPos(),getYPos());
    }

    @Override
    protected void onDraw(Canvas canvas){//each time a value of a cell is update we draw a new image on it

        super.onDraw(canvas);

        drawButton(canvas);



        if(isFlaged()){//check if we clicked for flag in GameEngine
            drawFlag(canvas);
        }
        else if (isRevealed() && isBomb() && !isClicked()){
            drawNormalBomb(canvas);
        }
        else{
            if(isClicked()){
                if (getValue() == -1) {
                    drawBombExploded(canvas);
                } else {
                    drawNumber(canvas);
                }

            }
            else{
                drawButton(canvas);
            }
        }
    }

    private void drawBombExploded(Canvas canvas) {//display the explode bomb
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.bomb);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNormalBomb(Canvas canvas) {//display the explode bomb
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.bomb);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawFlag(Canvas canvas) {//display the explode bomb
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.carrerjaune);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawButton(Canvas canvas){//display the basic button
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.carre);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawBomb(Canvas canvas){//display the bomb
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.carrerjaune);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNumber(Canvas canvas){//display the number after clicking on it
        Drawable drawable = null;

        switch(getValue()){
            case 0:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.carrergris);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.carrergris);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.number8);
                break;
        }
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);

    }
}
