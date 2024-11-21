package com.example.propuesta6_5;

import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Segunda extends AppCompatActivity {

    static final int MnOp1=1;
    static final int MnOp1_1=11;
    static final int MnOp1_2=12;
    static final int MnOp1_3=13;
    static final int MnOp1_4=14;
    static final int MnOp2=2;
    static final int MnOp2_1=21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu submenu1 = menu.addSubMenu(Menu.NONE, MnOp1, Menu.NONE, "DÍAS DE SAMANA");
        SubMenu submenu2 = menu.addSubMenu(Menu.NONE, MnOp2, Menu.NONE, "MESES DEL AÑO");
        submenu1.add(Menu.NONE, MnOp1_1, Menu.NONE, "LUNES");
        submenu1.add(Menu.NONE, MnOp1_2, Menu.NONE, "MARTES");
        submenu1.add(Menu.NONE, MnOp1_3, Menu.NONE, "MIÉRCOLES");
        submenu1.add(Menu.NONE, MnOp1_4, Menu.NONE, "JUEVES");
        //Terminar submenu

        return true;
    }
}