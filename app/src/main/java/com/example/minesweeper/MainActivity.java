package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static int onmark = 0;

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

    public void switchmode(View v){
        if(onmark == 1){
            onmark = 0;
        }
        else{
            onmark = 1;
        }
    }



}