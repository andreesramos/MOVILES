package com.example.andres_prueba3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lista=(ListView) findViewById(R.id.lista);
        ArrayList<Encapsulador> datos=new ArrayList<>();

        //Añade datos a la lista
        datos.add(new Encapsulador(R.drawable.im_buho, "BUHO", "Búho es el nombre común de aves de la familia Strigidae, del orden de las estrigiformes o aves rapaces nocturnas. Habitualmente designa especies que, a diferencia de las lechuzas, tienen plumas alzadas que parecen orejas."));
        datos.add(new Encapsulador(R.drawable.im_colibri, "COLIBRÍ", "Los troquilinos (Trochilinae) son una subfamilia de aves apodiformes de la familia Trochilidae, conocidas vulgarmente como colibríes, quindes, tucusitos, picaflores, chupamirtos, chuparrosas, huichichiquis (idioma nahuatl), mainumby (idioma guaraní) o guanumby. Conjuntamente con las ermitas, que pertenecen a la subfamilia Phaethornithinae, conforman la familia Trochilidae que, en la sistemática de Charles Sibley, se clasifica en un orden propio: Trochiliformes, independiente de los vencejos del orden Apodiformes. La subfamilia Trochilinae incluye más de 100 géneros que comprenden un total de 330 a 340 especies."));
        datos.add(new Encapsulador(R.drawable.im_cuervo, "CUERVO", "El cuervo común (Corvus corax) es una especie de ave paseriforme de la familia de los córvidos (Corvidae). Presente en todo el hemisferio septentrional, es la especie de córvido con la mayor superficie de distribución. Con el cuervo de pico grueso, es el mayor de los córvidos y probablemente la paseriforme más pesada; en su madurez, el cuervo común mide entre 52 y 69 centímetros de longitud y su peso varía de 0,69 a 1,7 kilogramos. Los cuervos comunes viven generalmente de 10 a 15 años pero algunos individuos han vivido 40 años. Los juveniles pueden desplazarse en grupos pero las parejas ya formadas permanecen juntas toda su vida, cada pareja defendiendo un territorio. Existen 8 subespecies conocidas que se diferencian muy poco aparentemente, aunque estudios recientes hayan demostrado diferencias genéticas significativas entre las poblaciones de distintas regiones."));
        datos.add(new Encapsulador(R.drawable.im_flamenco, "FLAMENCO", "Los fenicopteriformes (Phoenicopteriformes), los cuales reciben el nombre vulgar de flamencos, son un orden de aves neognatas, con un único género viviente: Phoenicopterus. Son aves que se distribuyen tanto por el hemisferio occidental como por el hemisferio oriental: existen cuatro especies en América y dos en el Viejo Mundo. Tienen cráneo desmognato holorrino, con 16 a 20 vértebras cervicales y pies anisodáctilos."));
        datos.add(new Encapsulador(R.drawable.im_kiwi, "KIWI", "Los kiwis (Apterix, gr. 'sin alas') son un género de aves paleognatas compuesto por cinco especies endémicas de Nueva Zelanda.1 2 Son aves no voladoras pequeñas, aproximadamente del tamaño de una gallina. Antes de la llegada de los humanos alrededor del año 1300, en Nueva Zelanda los únicos mamíferos que había eran murciélagos, y los nichos ecológicos que en otras partes del mundo eran ocupados por animales tan diversos como caballos, lobos y ratones fueron utilizados en Nueva Zelanda por pájaros (y en menor proporción por ciertas especies de reptiles). La denominación kiwi es maorí, idioma del pueblo homónimo de linaje malayopolinesio que colonizó Nueva Zelanda antes de la llegada de los europeos."));
        datos.add(new Encapsulador(R.drawable.im_loro,  "LORO", "Las Psitácidas (Psittacidae) son una familia de aves psitaciformes llamadas comúnmente loros o papagayos, e incluye a los guacamayos, las cotorras, los periquitos, los agapornis y formas afines."));
        datos.add(new Encapsulador(R.drawable.im_pavo, "PAVO", "Pavo es un género de aves galliformes de la familia Phasianidae, que incluye dos especies, el pavo real común (Pavo cristatus) y el pavo real cuelliverde (Pavo muticus).1"));
        datos.add(new Encapsulador(R.drawable.im_pinguino, "PINGÜINO", "Los pingüinos (familia Spheniscidae, orden Sphenisciformes) son un grupo de aves marinas, no voladoras, que se distribuyen únicamente en el Hemisferio Sur, sobre todo en sus altas latitudes."));

        //Añade a la lista un adaptador que vincula la lista con los datos
        lista.setAdapter(new Adaptador(this, R.layout.entrada, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if(entrada != null){
                    TextView texto_titulo_entrada = (TextView) view.findViewById(R.id.texto_titulo);
                    TextView texto_datos_entrada = (TextView) view.findViewById(R.id.texto_datos);
                    ImageView imagen_entrada=(ImageView) view.findViewById(R.id.imagen);
                    texto_titulo_entrada.setText(((Encapsulador) entrada).get_textoTitulo());
                    texto_datos_entrada.setText(((Encapsulador) entrada).get_textoContenido());
                    imagen_entrada.setImageResource(((Encapsulador) entrada).get_idImagen());
                    Encapsulador encapsulador = (Encapsulador) entrada;
                }
            }
        });

        //Imprime los datos del objeto seleccionado en un toast
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Encapsulador elegido = (Encapsulador) adapterView.getItemAtPosition(i);
                CharSequence textoElegido = "Seleccionado: " + elegido.get_textoContenido();
                Toast.makeText(MainActivity.this, textoElegido, Toast.LENGTH_LONG).show();
            }
        });

        //Elimina de la lista el elemento en el que se hace un click largo
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                datos.remove(i);
                ((BaseAdapter) lista.getAdapter()).notifyDataSetChanged();
                return true;
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