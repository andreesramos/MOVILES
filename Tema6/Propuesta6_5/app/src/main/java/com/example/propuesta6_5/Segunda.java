package com.example.propuesta6_5;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

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
    static final int MnOp2_2=22;
    static final int MnOp2_3=23;
    static final int MnOp2_4=24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu submenu1 = menu.addSubMenu(Menu.NONE, MnOp1, Menu.NONE, "DÍAS DE SAMANA").setIcon(R.drawable.logo);
        SubMenu submenu2 = menu.addSubMenu(Menu.NONE, MnOp2, Menu.NONE, "MESES DEL AÑO");
        submenu1.add(Menu.NONE, MnOp1_1, Menu.NONE, "LUNES");
        submenu1.add(Menu.NONE, MnOp1_2, Menu.NONE, "MARTES");
        submenu1.add(Menu.NONE, MnOp1_3, Menu.NONE, "MIÉRCOLES");
        submenu1.add(Menu.NONE, MnOp1_4, Menu.NONE, "JUEVES");
        submenu2.add(Menu.NONE, MnOp2_1, Menu.NONE, "ENERO");
        submenu2.add(Menu.NONE, MnOp2_2, Menu.NONE, "FEBRERO");
        submenu2.add(Menu.NONE, MnOp2_3, Menu.NONE, "MARZO");
        submenu2.add(Menu.NONE, MnOp2_4, Menu.NONE, "ABRIL");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String mensaje1="Pulsado ";
        String mensaje2="Pulsado el mes de ";
        int id=item.getItemId();
        TextView texto = (TextView) findViewById(R.id.texto);
        if(id == MnOp1_1){
            texto.setText(mensaje1 + "Lunes");
            return true;
        }else if(id == MnOp1_2){
            texto.setText(mensaje1 + "Martes");
            return true;
        }else if(id == MnOp1_3) {
            texto.setText(mensaje1 + "Miércoles");
            return true;
        }
        else if(id == MnOp1_4) {
            texto.setText(mensaje1 + "Jueves");
            return true;
        }
        else if(id == MnOp2_1) {
            texto.setText(mensaje2 + "Enero");
            return true;
        }
        else if(id == MnOp2_2) {
            texto.setText(mensaje2 + "Febrero");
            return true;
        }
        else if(id == MnOp2_3) {
            texto.setText(mensaje2 + "Marzo");
            return true;
        }else if(id == MnOp2_4) {
                texto.setText(mensaje2 + "Abril");
                return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}