package com.example.propuesta11_7;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import android.Manifest;
import android.content.pm.PackageManager;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions();
            }
        }

        surface=(SurfaceView) findViewById(R.id.surface);
        botonGrabar=(Button) findViewById(R.id.botonGrabar);
        botonPausa=(Button) findViewById(R.id.botonPausa);
        botonPlay=(Button) findViewById(R.id.botonPlay);
        ruta= Environment.getExternalStorageDirectory() + "/mivideo.mp4";

        surface.getHolder().addCallback(this);
        surface.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        botonGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (grabador == null) {
                    grabador = new MediaRecorder();
                }

                grabador.setAudioSource(MediaRecorder.AudioSource.MIC);
                grabador.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                grabador.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                grabador.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                grabador.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
                grabador.setOutputFile(ruta);

                try{
                    grabador.prepare();
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }

                if (grabador != null) {
                    grabador.start();
                    grabando = true;
                } else {
                    Toast.makeText(MainActivity.this, "Error, grabador no inicializado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        botonPausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grabando){
                    grabador.stop();
                    grabador.reset();
                    grabando=false;
                }else{
                    reproductor.stop();
                    reproductor.reset();
                    botonPlay.setText("PLAY");
                }
            }
        });

        botonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!reproductor.isPlaying()){
                    reproductor.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            botonGrabar.setEnabled(true);
                            botonPausa.setEnabled(false);
                            botonPlay.setEnabled(true);
                        }
                    });
                    if(reproductor.getCurrentPosition() == reproductor.getDuration()){
                        try{
                            reproductor.setDataSource(ruta);
                            reproductor.prepare();
                        }catch (IllegalStateException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                        reproductor.start();
                }else {
                    reproductor.pause();
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

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            if (grantResults.length > 0) {
                boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean audioAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                boolean storageAccepted = grantResults.length > 2 && grantResults[2] == PackageManager.PERMISSION_GRANTED;

                if (!cameraAccepted || !audioAccepted || !storageAccepted) {
                    // Mensaje si los permisos son rechazados
                    Toast.makeText(this, "Se necesitan permisos para grabar", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}