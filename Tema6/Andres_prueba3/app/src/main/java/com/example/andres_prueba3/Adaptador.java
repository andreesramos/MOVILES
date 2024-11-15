package com.example.andres_prueba3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class Adaptador extends BaseAdapter {
    private ArrayList<?> entradas;
    private int R_layout_idView;
    private Context contexto;

    public Adaptador(Context contexto, int R_layout_idView, ArrayList<?> entradas){
        super();
        this.contexto=contexto;
        this.entradas=entradas;
        this.R_layout_idView=R_layout_idView;
    }

    public abstract void onEntrada(Object entrada, View view);

    public int getCount(){return entradas.size();}

    public Object getItem(int posicion){return entradas.get(posicion);}

    public long getItemId(int posicion){return posicion;}

    public View getView(int posicion, View view, ViewGroup pariente){
        if(view == null){
            LayoutInflater vi=(LayoutInflater)
                    contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_idView, pariente, false);
        }
        onEntrada(entradas.get(posicion), view);
        return view;
    }
}
