package com.example.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class SpecialView extends View {
    Rect square;
    Paint rectPaint;

    public SpecialView(Context context){
        super(context);
        init(null,0);
    }
    
    public SpecialView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(attrs,0);
    }

    public SpecialView(Context context, AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        init(attrs,defStyle);
    }

    public void init(AttributeSet attrs,int defStyle){
        setBackgroundColor(Color.GRAY);
    }




}
