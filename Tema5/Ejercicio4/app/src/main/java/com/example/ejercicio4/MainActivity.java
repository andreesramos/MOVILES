package com.example.ejercicio4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText num1;
    EditText num2;
    CheckBox sumar;
    CheckBox restar;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        num1=(EditText) findViewById(R.id.num1);
        num2=(EditText) findViewById(R.id.num2);
        sumar=(CheckBox) findViewById(R.id.sumar);
        restar=(CheckBox) findViewById(R.id.restar);
        texto= findViewById(R.id.texto);

        //No funciona
        /*Integer n1=Integer.parseInt(String.valueOf(num1.getText()));
        Integer n2=Integer.parseInt(String.valueOf(num2.getText()));

        sumar.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener(){
                    public void onCheckedChanged(CompoundButton buttonView, boolean pulsado){
                        if(pulsado){
                            texto.append(" La suma es: " + (n1+n2));
                        }
                    }
                }
        );

        restar.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener(){
                    public void onCheckedChanged(CompoundButton buttonView, boolean pulsado){
                        if(pulsado){
                            texto.append(" La resta es: " + (n1-n2));
                        }
                    }
                }
        );*/
    }

    public void operar(View view){
        texto.setText("");
        Integer n1=Integer.parseInt(String.valueOf(num1.getText()));
        Integer n2=Integer.parseInt(String.valueOf(num2.getText()));
        if(sumar.isChecked()){
            texto.append(" La suma es: " + (n1+n2));
        }

        if(restar.isChecked()){
            texto.append(" La resta es: " + (n1-n2));
        }
    }
}