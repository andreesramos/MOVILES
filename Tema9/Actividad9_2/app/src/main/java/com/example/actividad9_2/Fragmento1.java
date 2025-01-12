package com.example.actividad9_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Fragmento1 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view= inflater.inflate(R.layout.activity_fragmento1, container, false);

        Button boton = (Button) view.findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragmento2 fragmento2 = new Fragmento2();
                FragmentManager FM = getParentFragmentManager();
                FragmentTransaction FT2 = FM.beginTransaction();
                FT2.replace(R.id.contenedor, fragmento2);
                FT2.commit();
            }
        });

        return view;
    }
}