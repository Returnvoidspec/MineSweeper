package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameEngine.getInstance().createGrid(this);

    }

    public void resetGame(View v){
        GameEngine.onLost = 0;
        GameEngine.getInstance().createGrid(this);
    }



}