package com.example.propuesta11_1;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textoAncho=(TextView) findViewById(R.id.textoAncho);
        TextView textoAlto=(TextView) findViewById(R.id.textoAlto);

        Display pantalla = getWindowManager().getDefaultDisplay();
        Point medida = new Point();
        pantalla.getSize(medida);
        int ancho=medida.x;
        int alto=medida.y;

        textoAncho.setText("Ancho: " + ancho);
        textoAlto.setText("Alto: " + alto);
    }
}