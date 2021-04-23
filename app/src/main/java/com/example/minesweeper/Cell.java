package com.example.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

public class Cell extends BaseCell implements View.OnClickListener {

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
    protected void onDraw(Canvas canvas){

        super.onDraw(canvas);

        drawButton(canvas);


        Log.e("", String.valueOf(isFlaged));
        if(isFlaged()){
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

    private void drawBombExploded(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.bomb);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNormalBomb(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.bomb);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawFlag(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.carrerjaune);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawButton(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.carre);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawBomb(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(),R.drawable.carrerjaune);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNumber(Canvas canvas){
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
