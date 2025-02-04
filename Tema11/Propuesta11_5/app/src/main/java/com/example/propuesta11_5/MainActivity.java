package com.example.propuesta11_5;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private float volumen;
    private float frecuencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button button1=(Button) findViewById(R.id.boton1);
        SeekBar seekBar1=(SeekBar) findViewById(R.id.seekBar1);
        Button button2=(Button) findViewById(R.id.boton2);
        SeekBar seekBar2=(SeekBar) findViewById(R.id.seekBar2);

        SoundPool sndPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 100);
        int soundId=sndPool.load(this, R.raw.gato, 1);
        SoundPool sndPool2 = new SoundPool(16, AudioManager.STREAM_MUSIC, 100);
        int soundId2=sndPool2.load(this, R.raw.laser, 1);

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volumen = progress / 200.0f;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sndPool.play(soundId, volumen, volumen, 1, 0, 1);
            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                frecuencia = 0.5f + (progress/200.0f) * 1.5f;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sndPool.play(soundId, 0.5f, 0.5f, 1, 0, frecuencia);
            }
        });
    }
}