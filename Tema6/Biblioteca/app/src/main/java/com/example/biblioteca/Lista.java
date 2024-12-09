package com.example.biblioteca;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Lista extends AppCompatActivity {

    private ListView lista;
    private ArrayList<Encapsulador> datos;
    private RatingBar rating;
    private Adaptador adaptador;
    HistorialManager historialManager=new HistorialManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);
        lista=(ListView) findViewById(R.id.lista);
        datos=new ArrayList<>();

        datos.add(new Encapsulador(R.drawable.quijote, "DON QUIJOTE DE LA MANCHA", "MIGUEL DE CERVANTES", 0, 2024, 10, 24));
        datos.add(new Encapsulador(R.drawable.principito, "EL PRINCIPITO", "ANTOINE DE SAINT-EXUPÉRY", 0, 2023, 6, 16));
        datos.add(new Encapsulador(R.drawable.anillos, "EL SEÑOR DE LOS ANILLOS", "J.R.R. TOLKIEN", 0, 2024, 0, 8));

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
                    miRating.setRating(((Lista.Encapsulador) entrada).get_rating());

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
            startActivityForResult(ins, 1);
            return true;
        } else if (id==R.id.listadoAutor) {
            ordenarPorAutor();
            return true;
        } else if (id==R.id.listadoRating) {
            ordenarPorRating();
            return true;
        } else if (id==R.id.historial) {
            mostrarHistorial();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if (requestCode == 1) {
                String titulo = data.getStringExtra("titulo");
                String autor = data.getStringExtra("autor");
                Float rating = data.getFloatExtra("rating", 0);
                int year = data.getIntExtra("year", 0);
                int month = data.getIntExtra("month", 0);
                int day = data.getIntExtra("day", 0);

                datos.add(new Encapsulador(R.drawable.logo, titulo, autor, rating, year, month, day));
                adaptador.notifyDataSetChanged();
                mostrarToast("Elemento insertado");
                historialManager.registrarAccion("Se insertó el libro '" + titulo + "'.");
            } else if (requestCode == 2) {
                int position = data.getIntExtra("position", -1);
                if (position != -1) {
                    String titulo = data.getStringExtra("titulo");
                    String autor = data.getStringExtra("autor");
                    float rating = data.getFloatExtra("rating", 0);
                    int year =data.getIntExtra("year", 0);
                    int month=data.getIntExtra("month", 0);
                    int day=data.getIntExtra("day", 0);

                    Encapsulador elemento = datos.get(position);
                    elemento.titulo = titulo;
                    elemento.autor = autor;
                    elemento.estrellas = rating;
                    elemento.year = year;
                    elemento.month = month;
                    elemento.day = day;

                    adaptador.notifyDataSetChanged();
                    mostrarToast("Elemento modificado");
                    historialManager.registrarAccion("Se editó el libro '" + titulo + "'.");
                }
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle("ELIGE UNA OPCION");
        inflater.inflate(R.menu.menu_lista, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        if(item.getItemId() == R.id.listar){
            listarElemento(position);
            return true;
        }else if(item.getItemId() == R.id.modificar){
            editarElemento(position);
            return true;
        }else if(item.getItemId() == R.id.eliminar){
            mostrarDialogo(position);
            return true;
        }else{
            return super.onContextItemSelected(item);
        }
    }

    private void editarElemento(int position){
        Encapsulador elemento=datos.get(position);

        Intent intent = new Intent(this, Insertar.class);
        intent.putExtra("isEdit", true);
        intent.putExtra("titulo", elemento.get_textoTitulo());
        intent.putExtra("autor", elemento.get_textoContenido());
        intent.putExtra("rating", elemento.get_rating());
        intent.putExtra("year", elemento.getYear());
        intent.putExtra("month", elemento.getMonth());
        intent.putExtra("day", elemento.getDay());
        intent.putExtra("position", position);

        startActivityForResult(intent, 2);
    }

    private void eliminarElemento(int position) {
        String t=datos.get(position).get_textoTitulo();
        datos.remove(position);
        adaptador.notifyDataSetChanged();
        mostrarToast("Elemento eliminado");
        historialManager.registrarAccion("Se eliminó el libro '" + t + "'.");
    }

    private void listarElemento(int position){
        Encapsulador elem=datos.get(position);

        Intent intent = new Intent(this, Mostrar.class);
        intent.putExtra("titulo", elem.get_textoTitulo());
        intent.putExtra("autor", elem.get_textoContenido());
        intent.putExtra("rating", elem.get_rating());
        intent.putExtra("year", elem.getYear());
        intent.putExtra("month", elem.getMonth());
        intent.putExtra("day", elem.getDay());
        intent.putExtra("position", position);

        startActivity(intent);
    }

    public void ordenarPorAutor(){
        Collections.sort(datos, new Comparator<Encapsulador>() {
            @Override
            public int compare(Encapsulador e1, Encapsulador e2) {
                return e1.get_textoContenido().compareTo(e2.get_textoContenido());
            }
        });
        adaptador.notifyDataSetChanged();
        mostrarToast("Lista ordenada por autor");
    }

    public void ordenarPorRating(){
        Collections.sort(datos, new Comparator<Encapsulador>() {
            @Override
            public int compare(Encapsulador e1, Encapsulador e2) {
                return Float.compare(e2.get_rating(), e1.get_rating());
            }
        });
        adaptador.notifyDataSetChanged();
        mostrarToast("Lista ordenada por rating");
    }

    public void mostrarHistorial(){
        Intent intent=new Intent(this, Historial.class);
        startActivity(intent);
    }

    private void mostrarToast(String mensaje){
        LayoutInflater inflater=getLayoutInflater();
        View layout=inflater.inflate(R.layout.toast, null);

        TextView textoToast=layout.findViewById(R.id.textoToast);
        ImageView imagenToast=layout.findViewById(R.id.imagenToast);

        textoToast.setText(mensaje);

        Toast toast=new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }

    public void mostrarDialogo(int position){
        AlertDialog.Builder builder=new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        builder.setMessage("¿SEGURO QUE QUIERES ELIMINAR?")
                .setTitle("CONFIRMACIÓN")
                .setIcon(R.drawable.eliminar);

        builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                eliminarElemento(position);
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Se ha cancelado la eliminacion", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialogo= builder.create();
        dialogo.show();
    }

    class Encapsulador{
        private int imagen;
        private String titulo, autor;
        private float estrellas;
        private int year, month, day;

        public Encapsulador(int idImagen, String textoTitulo, String textoAutor, float numEstrellas, int year, int month, int day){
            this.imagen=idImagen;
            this.titulo=textoTitulo;
            this.autor=textoAutor;
            this.estrellas =numEstrellas;
            this.year=year;
            this.month=month;
            this.day=day;
        }

        public String get_textoTitulo(){return titulo;}
        public String get_textoContenido(){return autor;}
        public int get_idImagen(){return imagen;}
        public float get_rating(){return estrellas;}
        public int getYear(){return year;}
        public int getMonth(){return month;}
        public int getDay(){return day;}
    }
}

