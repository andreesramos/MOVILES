package com.example.ejemplospreferencia;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Button;

public class OpcionesPreferencias extends PreferenceActivity {

    Button boton=(Button) findViewById(R.id.boton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ejemplos);

        /*boton.setOnClickListener(new View.OnClickListener()){
            @Override
            public void onClick(View v){
                startActivity(new Intent(OpcionesPreferencias.this, OpcionesPreferencias.class));
            }
        }*/
    }



}