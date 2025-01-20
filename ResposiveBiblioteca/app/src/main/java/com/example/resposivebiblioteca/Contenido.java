package com.example.resposivebiblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contenido {

    public static ArrayList<Lista_entrada> ENT_LISTA = new ArrayList<Lista_entrada>();

    public static Map<String, Lista_entrada> ENT_LISTA_HASHMAP = new HashMap<String, Lista_entrada>();

    public static class Lista_entrada{
        private int imagen;
        private String id, titulo, autor;
        private float estrellas;
        private int year, month, day;

        public Lista_entrada(String id, int idImagen, String textoTitulo, String textoAutor, float numEstrellas, int year, int month, int day){
            this.id=id;
            this.imagen=idImagen;
            this.titulo=textoTitulo;
            this.autor=textoAutor;
            this.estrellas =numEstrellas;
            this.year=year;
            this.month=month;
            this.day=day;
        }

        public String getId(){return id;}
        public String get_textoTitulo(){return titulo;}
        public String get_textoContenido(){return autor;}
        public int get_idImagen(){return imagen;}
        public float get_rating(){return estrellas;}
        public int getYear(){return year;}
        public int getMonth(){return month;}
        public int getDay(){return day;}

    }

    static{
        ponerEntrada(new Lista_entrada("0", R.drawable.quijote, "DON QUIJOTE DE LA MANCHA", "MIGUEL DE CERVANTES", 0, 2024, 10, 24));
        ponerEntrada(new Lista_entrada("1", R.drawable.principito, "EL PRINCIPITO", "ANTOINE DE SAINT-EXUPÉRY", 0, 2023, 6, 16));
        ponerEntrada(new Lista_entrada("2", R.drawable.anillos, "EL SEÑOR DE LOS ANILLOS", "J.R.R. TOLKIEN", 0, 2024, 0, 8));

    }

    private static void ponerEntrada(Lista_entrada entrada){
        ENT_LISTA.add(entrada);
        ENT_LISTA_HASHMAP.put(entrada.id, entrada);
    }
}