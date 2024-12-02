package com.example.propuesta7_2;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        Button boton = (Button) findViewById(R.id.boton1);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast toast=new Toast(getApplicationContext());

                LayoutInflater inflater=getLayoutInflater();
                View layout=inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.lytLayout));
                TextView txtMsg=(TextView) layout.findViewById(R.id.texto2);
                txtMsg.setText("TOAST PERSONALIZADO");
                toast.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            }
        });
    }
}