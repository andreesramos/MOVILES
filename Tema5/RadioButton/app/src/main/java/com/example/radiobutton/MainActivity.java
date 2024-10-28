package com.example.radiobutton;

import android.os.Bundle;
import android.widget.RadioGroup;

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
        RadioGroup miGrupo = (RadioGroup) findViewById(R.id.grupo);
        miGrupo.clearCheck();
        miGrupo.check(R.id.radio1);
        int idMarcado=miGrupo.getCheckedRadioButtonId();
    }
}