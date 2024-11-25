package com.example.enlace1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int MNU_OPC1 = 1;
    private static final int MNU_OPC2 = 2;
    private static final int MNU_OPC3 = 3;
    private static final int SMNU_OPC1=11;
    private static final int SMNU_OPC2=12;

    TextView lblMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lblMensaje= (TextView) findViewById(R.id.lblmensaje);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Alternativa 1
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Alternativa 2
        menu.add(Menu.NONE, MNU_OPC1, Menu.NONE, "Opcion1")
            .setIcon(android.R.drawable.ic_menu_preferences);
        menu.add(Menu.NONE, MNU_OPC2, Menu.NONE, "Opcion2")
            .setIcon(android.R.drawable.ic_menu_compass);

        SubMenu smnu = menu.
            addSubMenu(Menu.NONE, MNU_OPC1, Menu.NONE, "Opcion3")
                .setIcon(android.R.drawable.ic_menu_agenda);
        smnu.add(Menu.NONE, SMNU_OPC1, Menu.NONE, "Opcion 3.1");
        smnu.add(Menu.NONE, SMNU_OPC2, Menu.NONE, "Opcion 3.2");
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.MnuOpc1) {
            lblMensaje.setText("Opcion 1 pulsada!");
            return true;
        } else if (item.getItemId()==R.id.MnuOpc2) {
            lblMensaje.setText("Opcion 2 pulsada!");;
            return true;
        } else if (item.getItemId()==R.id.MnuOpc3) {
            lblMensaje.setText("Opcion 3 pulsada!");;
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}