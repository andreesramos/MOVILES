package com.example.optionsmenu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    static final int MnOp1=1;
    static final int MnOp2=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String mensaje="";
        int id=item.getItemId();
        if (id == R.id.MnOp1) {
            // Código para Opcion de menu A
            return true;
        } else if (id == R.id.MnOp2) {
            // Código para Opcion de menu B
            return true;
        } else if (id == R.id.MnOp3) {
            // Código para Opcion de menu C
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}