package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int onmark = 0;
    public static int bomb = GameEngine.BOMB_NUMBER;
    public static int bombLeft = GameEngine.BOMB_LEFT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameEngine.getInstance().createGrid(this);
        TextView textView = findViewById(R.id.textView3);
        textView.setText(String.valueOf(bomb));

        TextView textView2 = findViewById(R.id.textView4);
        textView2.setText(String.valueOf(bombLeft));

    }


    @Override
    public void onResume() {
        super.onResume();
        TextView textView2 = findViewById(R.id.textView4);
        textView2.setText(String.valueOf(bombLeft));
    }


    public void resetGame(View v){//when the button reset is pressed
        GameEngine.onLost = 0;
        GameEngine.getInstance().createGrid(this);
    }

    public void switchmode(View v){//when the button flag is pressed we switch mode with the variable onmark
        if(onmark == 1){
            onmark = 0;
        }
        else{
            onmark = 1;
        }
    }



}