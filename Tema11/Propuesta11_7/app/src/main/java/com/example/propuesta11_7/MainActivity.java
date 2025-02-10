package com.example.propuesta11_7;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private VideoView surfaceView;
    private Button botonGrabar, botonPausa, botonPlay;
    private MediaRecorder grabador;
    private MediaPlayer reproductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        surfaceView=(VideoView) findViewById(R.id.surfaceView);
        botonGrabar=(Button) findViewById(R.id.botonGrabar);
        botonPausa=(Button) findViewById(R.id.botonPausa);
        botonPlay=(Button) findViewById(R.id.botonPlay);

        surfaceView.getHolder().addCallback(this);

    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3){}

    public void surfaceCreated(SurfaceHolder arg0){}

    public void surfaceDestroyed(SurfaceHolder arg0){}
}