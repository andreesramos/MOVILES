package com.example.propuesta6_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    }

    public void primera(View view){
        Intent primera = new Intent(this, Primera.class);
        startActivity(primera);
    }

    public void segunda(View view){
        Intent segunda = new Intent(this, Segunda.class);
        startActivity(segunda);
    }
}