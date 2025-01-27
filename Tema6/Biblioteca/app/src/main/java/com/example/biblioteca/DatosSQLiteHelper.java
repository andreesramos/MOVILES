package com.example.biblioteca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatosSQLiteHelper /*extends SQLiteOpenHelper*/ {

    /*String sqlCreate = "CREATE TABLE Datos (id INTEGER PRIMARY KEY AUTOINCREMENT, imagen INTEGER, titulo TEXT, autor TEXT, estrellas REAL, year INTEGER, month INTEGER, day INTEGER)";

    public DatosSQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);

        db.execSQL("INSERT INTO Datos (imagen, titulo, autor, estrellas, year, month, day) VALUES (R.drawable.quijote, 'DON QUIJOTE DE LA MANCHA', 'MIGUEL DE CERVANTES', 0, 2024, 10, 24)");
        db.execSQL("INSERT INTO Datos (imagen, titulo, autor, estrellas, year, month, day) VALUES (R.drawable.principito, 'EL PRINCIPITO', 'ANTOINE DE SAINT-EXUPÉRY', 0, 2023, 6, 16)");
        db.execSQL("INSERT INTO Datos (imagen, titulo, autor, estrellas, year, month, day) VALUES (R.drawable.anillos, 'EL SEÑOR DE LOS ANILLOS', 'J.R.R. TOLKIEN', 0, 2024, 0, 8)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS Datos");
        db.execSQL(sqlCreate);
    }*/
}
