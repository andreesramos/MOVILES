package com.example.biblioteca;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Insertar extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 100; // Código de solicitud de la cámara
    private static final int CAMERA_PERMISSION_CODE = 101; // Código de permiso para la cámara

    private EditText tituloInsertar;
    private EditText autorInsertar;
    private RatingBar ratingInsertar;
    private DatePicker fechaInsertar;
    private Button botonGuardar;
    private FloatingActionButton botonCamara;
    private String imagenPath;

    private Bitmap fotoBitmap; // Variable para almacenar la imagen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insertar);

        tituloInsertar=(EditText) findViewById(R.id.tituloInsertar);
        autorInsertar=(EditText) findViewById(R.id.autorInsertar);
        ratingInsertar=(RatingBar) findViewById(R.id.ratingInsertar);
        fechaInsertar=(DatePicker) findViewById(R.id.fechaInsertar);
        botonGuardar=(Button) findViewById(R.id.botonGuardar);
        botonCamara=(FloatingActionButton) findViewById(R.id.botonCamara);

        // Verificar y solicitar permisos de cámara
        verificarPermisos();

        Intent intent=getIntent();
        if(intent!=null){
            boolean isEdit = intent.getBooleanExtra("isEdit", false);

            tituloInsertar.setText(intent.getStringExtra("titulo"));
            autorInsertar.setText(intent.getStringExtra("autor"));
            ratingInsertar.setRating(intent.getFloatExtra("rating", 0));

            int year = intent.getIntExtra("year", 2024);
            int month = intent.getIntExtra("month", 0);
            int day = intent.getIntExtra("day", 1);
            fechaInsertar.updateDate(year, month, day);

            if(isEdit){
                tituloInsertar.setEnabled(false);
            }
        }

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDatos();
            }
        });

        // Configurar el boton de la camara
        botonCamara.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                abrirCamara();
            } else {
                // Solicitar permiso de cámara si no está concedido
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
            }
        });

    }

    // Método para verificar permisos de cámara al iniciar la actividad
    private void verificarPermisos() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }
    }

    // Método para manejar la respuesta del usuario sobre los permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso de cámara concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Metodo para abrir la cámara
    private void abrirCamara() {
        Intent intentCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentCamara.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intentCamara, REQUEST_CAMERA);
        } else {
            Toast.makeText(this, "No se puede abrir la cámara", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imagenPath = null; // Liberar memoria
    }

    // Recibir la imagen de la camara
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap fotoBitmap = (Bitmap) data.getExtras().get("data");

                if (fotoBitmap != null) {
                    imagenPath = guardarImagenEnInterno(fotoBitmap);
                    Toast.makeText(this, "Foto guardada correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No se pudo capturar la imagen", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No se recibió ninguna imagen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String guardarImagenEnInterno(Bitmap bitmap) {
        File directory = getFilesDir();
        File imageFile = new File(directory, "captura_" + System.currentTimeMillis() + ".jpg");

        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            return imageFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void enviarDatos(){
        String titulo = tituloInsertar.getText().toString();
        String autor = autorInsertar.getText().toString();
        float rating = ratingInsertar.getRating();
        int year = fechaInsertar.getYear();
        int month = fechaInsertar.getMonth();
        int day = fechaInsertar.getDayOfMonth();
        int position = getIntent().getIntExtra("position", -1);

        Intent intent=new Intent();
        intent.putExtra("isEdit", false);
        intent.putExtra("titulo", titulo);
        intent.putExtra("autor", autor);
        intent.putExtra("rating", rating);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("position", position);
        intent.putExtra("imagenPath", imagenPath); //Enviar la imagen capturada

        setResult(RESULT_OK, intent);
        finish();
    }
}