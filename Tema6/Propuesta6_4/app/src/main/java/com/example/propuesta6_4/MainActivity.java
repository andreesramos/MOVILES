package com.example.propuesta6_4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    //private GridView lista;
    //private Spinner lista;
    private TextView texto;
    private RadioButton radioButton_pulsado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lista=(ListView) findViewById(R.id.lista);
        //lista=(GridView) findViewById(R.id.lista);
        //lista=(Spinner) findViewById(R.id.lista);
        texto=(TextView) findViewById(R.id.texto);
        ArrayList<Encapsulador> datos=new ArrayList<Encapsulador>();

        datos.add(new Encapsulador(R.drawable.ima1, "DONUTS", "El 15 de septiembre de 2009, fue lanzado el SDK de Android 1.6 Donut, basado en el núcleo Linux 2.6.29. En la actualización se incluyen numerosas características nuevas.", true));
        datos.add(new Encapsulador(R.drawable.ima2, "FROYO", "El 20 de mayo de 2010, El SDK de Android 2.2 Froyo (Yogur helado) fue lanzado, basado en el núcleo Linux 2.6.32.", false));
        datos.add(new Encapsulador(R.drawable.ima3, "GINGERBREAD", "El 6 de diciembre de 2010, el SDK de Android 2.3 Gingerbread (Pan de Jengibre) fue lanzado, basado en el núcleo Linux 2.6.35.", false));
        datos.add(new Encapsulador(R.drawable.ima4, "HONEYCOMB", "El 22 de febrero de 2011, sale el SDK de Android 3.0 Honeycomb (Panal de Miel). Fue la primera actualización exclusiva para TV y tableta, lo que quiere decir que sólo es apta para TV y tabletas y no para teléfonos Android.", false));
        datos.add(new Encapsulador(R.drawable.ima5, "ICE CREAM", "El SDK para Android 4.0.0 Ice Cream Sandwich (Sándwich de Helado), basado en el núcleo de Linux 3.0.1, fue lanzado públicamente el 12 de octubre de 2011.", false));
        datos.add(new Encapsulador(R.drawable.ima6, "JELLY BEAN", "Google anunció Android 4.1 Jelly Bean (Gomita Confitada o Gominola) en la conferencia del 30 de junio de 2012. Basado en el núcleo de linux 3.0.31, Bean fue una actualización incremental con el enfoque primario de mejorar la funcionalidad y el rendimiento de la interfaz de usuario.", false));
        datos.add(new Encapsulador(R.drawable.ima7, "KITKAT", "Su nombre se debe a la chocolatina KitKat, de la empresa internacional Nestlé. Posibilidad de impresión mediante WIFI. WebViews basadas en el motor de Chromium.", false));
        datos.add(new Encapsulador(R.drawable.ima8, "LOLLIPOP", "Incluye Material Design, un diseño intrépido, colorido, y sensible interfaz de usuario para las experiencias coherentes e intuitivos en todos los dispositivos. Movimiento de respuesta natural, iluminación y sombras realistas y familiares elementos visuales hacen que sea más fácil de navegar su dispositivo.", false));

        lista.setAdapter(new Adaptador(this, R.layout.entrada, datos){
            @Override
            public void onEntrada(Object entrada, View view){
                if (entrada != null){
                    TextView texto_superior_entrada=(TextView) view.findViewById(R.id.texto_titulo);
                    TextView texto_inferior_entrada=(TextView) view.findViewById(R.id.texto_datos);
                    ImageView imagen_entrada=(ImageView) view.findViewById(R.id.imagen);
                    RadioButton miRadio=(RadioButton) view.findViewById(R.id.boton);
                    texto_superior_entrada.setText(((Encapsulador) entrada).get_textoTitulo());
                    texto_inferior_entrada.setText(((Encapsulador) entrada).get_textoContenido());
                    imagen_entrada.setImageResource(((Encapsulador) entrada).get_idImagen());
                    Encapsulador encapsulador = (Encapsulador) entrada;

                    //Marcada una opcion
                    /*miRadio.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            if(radioButton_pulsado != null) radioButton_pulsado.setChecked(false);
                            radioButton_pulsado = (RadioButton) v;
                            texto.setText("MARCADA UNA OPCION");
                        }
                    });*/

                    miRadio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(radioButton_pulsado != null) radioButton_pulsado.setChecked(false);
                            radioButton_pulsado=miRadio;
                            radioButton_pulsado.setChecked(true);
                            texto.setText(encapsulador.get_textoContenido());
                        }
                    });
                }
            }
        });

        //Muestra info al pulsar en el objeto del listview
        /*lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Encapsulador elegido = (Encapsulador) adapterView.getItemAtPosition(i);
                CharSequence textoElegido = "Seleccionado: " + elegido.get_textoContenido();
                texto.setText(textoElegido);
            }
        });*/

    }

    class Encapsulador{
        private int imagen;
        private String titulo, texto;
        private boolean dato1;

        public Encapsulador(int idImagen, String textoTitulo, String textoContenido, boolean favorito){
            this.imagen=idImagen;
            this.titulo=textoTitulo;
            this.texto=textoContenido;
            this.dato1=favorito;
        }

        public String get_textoTitulo(){return titulo;}
        public String get_textoContenido(){return texto;}
        public int get_idImagen(){return imagen;}
        public boolean get_checkBox1(){return dato1;}
    }
}