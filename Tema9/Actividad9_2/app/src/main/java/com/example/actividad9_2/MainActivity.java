package com.example.actividad9_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    FragmentManager FM = getSupportFragmentManager();
    FragmentTransaction FT = FM.beginTransaction();
    Button boton = (Button) findViewById(R.id.boton);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Fragmento1 fragmento1 = new Fragmento1();
        Fragmento2 fragmento2 = new Fragmento2();
        FT.add(R.id.contenedor, fragmento1);
        FT.commit();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FT.replace(R.id.contenedor, fragmento2);
                FT.commit();
            }
        });
    }

}