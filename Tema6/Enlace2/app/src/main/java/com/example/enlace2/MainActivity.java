package com.example.enlace2;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView lblMensaje;
    ListView lstLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lblMensaje=(TextView) findViewById(R.id.lblMensaje);
        lstLista=(ListView) findViewById(R.id.lstLista);

        String datos[]= new String[]{"Elem1", "Elem2", "Elem3", "Elem4", "Elem5"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        lstLista.setAdapter(adaptador);

        registerForContextMenu(lblMensaje);
        registerForContextMenu(lstLista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        if(v.getId() == R.id.lblMensaje){
            inflater.inflate(R.menu.menu_ctx_etiqueta, menu);
        }else if (v.getId() == R.id.lstLista){
            AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) menuInfo;

            menu.setHeaderTitle(lstLista.getAdapter().getItem(info.position).toString());

            inflater.inflate(R.menu.menu_ctx_lista, menu);
        }


    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == R.id.CtxLblOpc1){
            lblMensaje.setText("Etiqueta: Opcion 1 pulsada");
            return true;
        }else if(item.getItemId() == R.id.CtxLblOpc2) {
            lblMensaje.setText("Etiqueta: Opcion 2 pulsada");
            return true;
        }else if(item.getItemId() == R.id.CtxLstOpc1) {
            lblMensaje.setText("Lista[" + info.position + "] Opcion 1 pulsada");
            return true;
        }else if(item.getItemId() == R.id.CtxLstOpc2) {
            lblMensaje.setText("Lista[" + info.position + "] Opcion 2 pulsada");
            return true;
        }else{
            return super.onContextItemSelected(item);
        }
    }
}