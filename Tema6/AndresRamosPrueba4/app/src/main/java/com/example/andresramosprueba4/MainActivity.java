package com.example.andresramosprueba4;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        spinner=(Spinner) findViewById(R.id.spinner);

        ArrayList<Encapsulador> datos=new ArrayList<>();

        datos.add(new Encapsulador(R.drawable.caravaggio, "CARAVAGGIO", "Pintor italiano entre los años de 1593 y 1610. Es considerado como el primer gran exponente de la pintura del Barroco."));
        datos.add(new Encapsulador(R.drawable.rafael, "RAFAEL SANZIO", "Pintor y arquitecto italiano del Renacimiento, realizó importantes aportes en la arquitectura y, como inspector de antigüedades."));
        datos.add(new Encapsulador(R.drawable.velazquez, "VELAZQUEZ", "Pintor Barroco nacido en Sevilla en 1599, es considerado uno de los máximos exponentes de la pintura española y maestro de la pintura universal."));
        datos.add(new Encapsulador(R.drawable.miguelangel, "MIGUEL ANGEL", "Arquitecto, escultor y pintor italiano renacentista, considerado uno de los más grandes artistas de la historia."));
        datos.add(new Encapsulador(R.drawable.rembrant, "REMBRANDT", "Pintor y grabador holandés. La historia del arte le considera uno de los mayores maestros barrocos de la pintura y el grabado."));
        datos.add(new Encapsulador(R.drawable.boticelli, "BOTICELLI", "Apodado Sandro Botticelli, fue un pintor cuatrocentista italiano.su obra se ha considerado representativa de la pintura del primer Renacimiento."));
        datos.add(new Encapsulador(R.drawable.leonardo, "LEONARDO DA VINCI", "Notable polímata del Renacimiento italiano (a la vez anatomista, arquitecto, artista, botánico, científico, escritor, escultor, filósofo,ingeniero...)"));
        datos.add(new Encapsulador(R.drawable.renoir, "RENOIR", "Pintor francés impresionista, interesado por la pintura de cuerpos femeninos en paisajes, inspirados a menudo en pinturas clásicas renacentistas y barrocas."));

        spinner.setAdapter(new Adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if(entrada!=null){
                    TextView nombre=(TextView) view.findViewById(R.id.nombre);
                    TextView info=(TextView) view.findViewById(R.id.info);
                    ImageView imagen=(ImageView) view.findViewById(R.id.imagen);
                    WebView webView=(WebView) findViewById(R.id.web);
                    nombre.setText(((Encapsulador) entrada).get_textoTitulo());
                    info.setText(((Encapsulador) entrada).get_textoContenido());
                    imagen.setImageResource(((Encapsulador) entrada).get_idImagen());

                    /*spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            if(i == 0){
                                webView.loadUrl("http://es.wikipedia.org/wiki/Caravaggio");
                            } else if (i==1) {
                                webView.loadUrl("http://es.wikipedia.org/wiki/Rafael_Sanzio");
                            } else if (i==2) {
                                webView.loadUrl("http://es.wikipedia.org/wiki/Diego_Vel%C3%A1zquez");
                            } else if (i==3) {
                                webView.loadUrl("http://es.wikipedia.org/wiki/Miguel_%C3%81ngel");
                            } else if (i==4) {
                                webView.loadUrl("http://es.wikipedia.org/wiki/Rembrandt");
                            } else if (i==5) {
                                webView.loadUrl("http://es.wikipedia.org/wiki/Sandro_Botticelli");
                            } else if (i==6) {
                                webView.loadUrl("http://es.wikipedia.org/wiki/Leonardo_da_Vinci");
                            } else if (i==7) {
                                webView.loadUrl("http://es.wikipedia.org/wiki/Pierre-Auguste_Renoir");
                            }
                        }
                    });*/
                }
            }
        });
    }


    class Encapsulador{
        private int imagen;
        private String titulo, texto;

        public Encapsulador(int idImagen, String textoTitulo, String textoContenido){
            this.imagen=idImagen;
            this.titulo=textoTitulo;
            this.texto=textoContenido;
        }

        public String get_textoTitulo(){return titulo;}
        public String get_textoContenido(){return texto;}
        public int get_idImagen(){return imagen;}
    }
}