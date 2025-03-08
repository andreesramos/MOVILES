package com.example.pacman2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));

        SoundManager.initSounds(this);
        SoundManager.playBackgroundMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoundManager.release();
    }

}