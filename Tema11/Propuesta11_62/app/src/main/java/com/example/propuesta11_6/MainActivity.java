package com.example.propuesta11_6;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageButton grabar, parar, play;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String salida;
    private static final int REQUEST_PERMISSION = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grabar = findViewById(R.id.grabar);
        parar = findViewById(R.id.parar);
        play = findViewById(R.id.play);

        parar.setEnabled(false);
        play.setEnabled(false);

        salida = getExternalFilesDir(Environment.DIRECTORY_MUSIC) + "/audio_record.3gp";

        verficarPermisos();

        grabar.setOnClickListener(view -> iniciarGrabacion());
        parar.setOnClickListener(view -> detenerGrabacion());
        play.setOnClickListener(view -> reproducirAudio());
    }

    private void verficarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION);
            } else {
                grabar.setEnabled(true);
            }
        }
    }

    private void iniciarGrabacion() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permiso de grabación no concedido", Toast.LENGTH_SHORT).show();
            return;
        }

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(salida);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            Toast.makeText(this, "Grabando...", Toast.LENGTH_SHORT).show();
            grabar.setEnabled(false);
            parar.setEnabled(true);
            play.setEnabled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void detenerGrabacion() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            Toast.makeText(this, "Grabación finalizada", Toast.LENGTH_SHORT).show();
            grabar.setEnabled(true);
            parar.setEnabled(false);
            play.setEnabled(true);
        }
    }

    private void reproducirAudio() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(salida);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(this, "Reproduciendo...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
