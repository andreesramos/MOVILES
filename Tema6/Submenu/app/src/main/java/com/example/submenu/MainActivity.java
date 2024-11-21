package com.example.submenu;

import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    static final int MnOp1=1;
    static final int MnOp2=2;
    static final int MnOp1_1=11;
    static final int MnOp1_2=12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        SubMenu smnu= menu.addSubMenu(Menu.NONE, MnOp1, Menu.NONE, "Opcion A desde Java");
        smnu.add(Menu.NONE, MnOp1_1, Menu.NONE, "Opcion A.1");
        smnu.add(Menu.NONE, MnOp1_2, Menu.NONE, "Opcion A.2");
        menu.add(Menu.NONE, MnOp2, Menu.NONE, "Opcion B desde Java");
        getMenuInflater().inflate(R.menu.opciones, menu);
        return true;
    }
}