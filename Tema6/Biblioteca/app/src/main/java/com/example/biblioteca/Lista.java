package com.example.biblioteca;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    private ListView lista;
    private ArrayList<Encapsulador> datos;
    private RatingBar rating;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);
        lista=(ListView) findViewById(R.id.lista);
        datos=new ArrayList<>();

        datos.add(new Encapsulador(R.drawable.quijote, "DON QUIJOTE DE LA MANCHA", "MIGUEL DE CERVANTES", 0));
        datos.add(new Encapsulador(R.drawable.principito, "EL PRINCIPITO", "ANTOINE DE SAINT-EXUPÉRY", 0));
        datos.add(new Encapsulador(R.drawable.anillos, "EL SEÑOR DE LOS ANILLOS", "J.R.R. TOLKIEN", 0));

        adaptador = new Adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if(entrada!=null){
                    TextView texto_superior_entrada=(TextView) view.findViewById(R.id.texto_titulo);
                    TextView texto_inferior_entrada=(TextView) view.findViewById(R.id.titulo_autor);
                    ImageView imagen_entrada=(ImageView) view.findViewById(R.id.imagen);
                    RatingBar miRating=(RatingBar) view.findViewById(R.id.rating);
                    texto_superior_entrada.setText(((Lista.Encapsulador) entrada).get_textoTitulo());
                    texto_inferior_entrada.setText(((Lista.Encapsulador) entrada).get_textoContenido());
                    imagen_entrada.setImageResource(((Lista.Encapsulador)entrada).get_idImagen());

                    //onClickListener
                }
            }
        };
        lista.setAdapter(adaptador);
        registerForContextMenu(lista);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.insertar){
            Intent ins=new Intent(this, Insertar.class);
            startActivity(ins);
            return true;
        }return super.onOptionsItemSelected(item);
    }

    public void insertar(Encapsulador nuevo){
        datos.add(nuevo);
        adaptador.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle("ELIGE UNA OPCION");
        inflater.inflate(R.menu.menu_lista, menu);
    }


    class Encapsulador{
        private int imagen;
        private String titulo, autor;
        private float dato1;

        public Encapsulador(int idImagen, String textoTitulo, String textoAutor, float numEstrellas){
            this.imagen=idImagen;
            this.titulo=textoTitulo;
            this.autor=textoAutor;
            this.dato1=numEstrellas;
        }

        public String get_textoTitulo(){return titulo;}
        public String get_textoContenido(){return autor;}
        public int get_idImagen(){return imagen;}
        public float get_rating(){return dato1;}
    }
}

