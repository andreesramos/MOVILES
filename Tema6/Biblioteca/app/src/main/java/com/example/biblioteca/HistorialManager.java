package com.example.biblioteca;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.android.material.datepicker.SingleDateSelector;

public class HistorialManager {
    private static final String fileName = "historial_acciones.txt";
    private Context context;

    public HistorialManager(Context context){
        this.context=context;
    }

    // Método para registrar una acción
    public void registrarAccion(String accion){
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        String regsitro = timestamp + " - " + accion + "\n";

        try{
            FileOutputStream salida = context.openFileOutput(fileName, Context.MODE_APPEND);
            salida.write(regsitro.getBytes());
            salida.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Método para leer el historial
    public String leerHistorial(){
        File file = new File(context.getFilesDir(), fileName);
        if(!file.exists()){
            return "No hay acciones registradas";
        }

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput(fileName)))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                stringBuilder.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al leer el historial.";
        }

        return stringBuilder.toString();
    }
}
