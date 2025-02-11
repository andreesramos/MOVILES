package com.example.propuesta11_7;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView surface;
    private Button botonGrabar, botonPausa, botonPlay;
    private MediaRecorder grabador;
    private MediaPlayer reproductor;
    private String ruta;
    private boolean grabando = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Solo en API 23+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 100);
            }
        }


        surface=(SurfaceView) findViewById(R.id.surface);
        botonGrabar=(Button) findViewById(R.id.botonGrabar);
        botonPausa=(Button) findViewById(R.id.botonPausa);
        botonPlay=(Button) findViewById(R.id.botonPlay);
        ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/mivideo.mp4";

        surface.getHolder().addCallback(this);
        surface.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        botonGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (grabador != null) {
                    grabador.reset();
                }else{
                    grabador = new MediaRecorder();
                }

                try{
                    grabador.setAudioSource(MediaRecorder.AudioSource.MIC);
                    grabador.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    grabador.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    grabador.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    grabador.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
                    grabador.setOutputFile(ruta);

                    grabador.prepare();
                    grabador.start();
                    grabando = true;

                    botonGrabar.setEnabled(false);
                    botonPausa.setEnabled(true);
                    botonPlay.setEnabled(false);

                    Toast.makeText(MainActivity.this, "Grabando...", Toast.LENGTH_SHORT).show();
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error al iniciar la grabación", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonPausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grabando){
                    try{
                        grabador.stop();
                        grabador.release();
                        grabador=null;
                        grabando=false;

                        botonGrabar.setEnabled(true);
                        botonPausa.setEnabled(false);
                        botonPlay.setEnabled(true);

                        Toast.makeText(MainActivity.this, "Grabación detenida", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Error al detener la grabación", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "No se está grabando", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reproductor == null) {
                    reproductor = new MediaPlayer();
                }

                try {
                    reproductor.setDataSource(ruta);
                    reproductor.setDisplay(surface.getHolder());
                    reproductor.prepare();
                    reproductor.start();

                    botonGrabar.setEnabled(false);
                    botonPausa.setEnabled(false);
                    botonPlay.setEnabled(false);

                    reproductor.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            botonGrabar.setEnabled(true);
                            botonPausa.setEnabled(false);
                            botonPlay.setEnabled(true);
                        }
                    });

                    Toast.makeText(MainActivity.this, "Reproduciendo...", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error al reproducir", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3){

    }

    public void surfaceCreated(SurfaceHolder holder){
        if(grabador == null){
            grabador=new MediaRecorder();
            grabador.setPreviewDisplay(holder.getSurface());
        }
        if(reproductor == null){
            reproductor = new MediaPlayer();
            reproductor.setDisplay(holder);
        }
    }

    public void surfaceDestroyed(SurfaceHolder arg0){
        if (grabador != null) {
            grabador.release();
            grabador = null;
        }
        if (reproductor != null) {
            reproductor.release();
            reproductor = null;
        }
    }


}