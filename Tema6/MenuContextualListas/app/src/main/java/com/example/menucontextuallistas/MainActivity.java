package com.example.menucontextuallistas;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        String datos[]=new String[]{"OPCION DE MENU A", "OPCION DE MENU B", "OPCION DE MENU C", "OPCION DE MENU D", "OPCION DE MENU E"};
        lista=(ListView) findViewById(R.id.listado);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        lista.setAdapter(adaptador);
        registerForContextMenu(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(lista.getAdapter().getItem(info.position).toString());
        if(info.position == 0){
            inflater.inflate(R.menu.menu1, menu);
        } else if (info.position == 1) {
            inflater.inflate(R.menu.menu2, menu);
        } else if (info.position == 2) {
            inflater.inflate(R.menu.menu3, menu);
        } else if (info.position == 3) {
            inflater.inflate(R.menu.menu3, menu);
        } else if (info.position == 4) {
            inflater.inflate(R.menu.menu4, menu);
        } else if (info.position == 5) {
            inflater.inflate(R.menu.menu5, menu);
        }
    }
}