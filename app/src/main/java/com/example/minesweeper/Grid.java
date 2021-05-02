package com.example.minesweeper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class Grid extends GridView {//create the grid
    public Grid(Context context, AttributeSet attrs) {
        super(context, attrs);
        setNumColumns(GameEngine.WIDTH);
        setAdapter(new GridAdapter());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){//to fit the grid to the content layout
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter {//wr fill the grid with clickable item

        @Override
        public int getCount() {//we define the Witdh and height of each item in the grid
            return GameEngine.getInstance().WIDTH*GameEngine.getInstance().HEIGHT;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }//get function to get position of item

        @Override
        public long getItemId(int position) {
            return 0;
        }//and te reference of each one

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {//get the view to get the click
            return GameEngine.getInstance().getCellAt(position);
        }
    }
}
