package com.example.andresramos_pruebatema9;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements Fragmento1.FragmentInteractionListener{

    private Fragmento2 fragmento2;
    FragmentManager FM = getSupportFragmentManager();
    FragmentTransaction FT = FM.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Fragmento1 fragmento1 = new Fragmento1();
        fragmento2 = new Fragmento2();
        FT.add(R.id.contenedor1, fragmento1);
        FT.add(R.id.contenedor2, fragmento2);
        FT.commit();
    }

    public void enviarTexto(String texto){
        fragmento2.actualizarTexto(texto);
    }
}