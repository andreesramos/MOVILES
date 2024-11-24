package com.example.ejercicio6_9;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.lista);

        String[] datos = new String[]{"OPCION A", "OPCION B", "OPCION C", "OPCION D", "OPCION E"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        lista.setAdapter(adaptador);

        registerForContextMenu(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        AdapterContextMenuInfo info=(AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(lista.getAdapter().getItem(info.position).toString());
        if(info.position==0){
            inflater.inflate(R.menu.menu_lista1, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String selectedOpction = (String) lista.getAdapter().getItem(info.position);
        if (item.getItemId() == R.id.ListaOpA1) {
            Toast.makeText(this, "Opcion 1A seleccionada", Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId() == R.id.ListaOpA2){
            Toast.makeText(this, "Opcion 2A seleccionada", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return super.onContextItemSelected(item);
        }
    }
}