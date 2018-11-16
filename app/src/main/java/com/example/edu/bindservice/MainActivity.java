package com.example.edu.bindservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlayer = findViewById(R.id.buttonPlayer);
        buttonPlayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
