package com.example.biblioteca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BaseDatos extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "usuarios.db";
    private static final int DATABASE_VERSION = 1;

    // Nombre de la tabla y columnas
    private static final String TABLE_USUARIOS = "usuarios";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USUARIO = "usuario";
    private static final String COLUMN_CONTRASENA = "contrasena";

    // Consulta para crear la tabla
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_USUARIOS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USUARIO + " TEXT NOT NULL, " +
            COLUMN_CONTRASENA + " TEXT NOT NULL);";

    public BaseDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE); // Crear la tabla
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db); // Volver a crear la tabla
    }

    // Métodos para gestionar usuarios
    public long insertarUsuario(String usuario, String contrasena) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USUARIO, usuario);
        values.put(COLUMN_CONTRASENA, contrasena);
        return db.insert(TABLE_USUARIOS, null, values);
    }

    public boolean validarUsuario(String usuario, String contrasena) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USUARIOS +
                " WHERE " + COLUMN_USUARIO + " = ? AND " + COLUMN_CONTRASENA + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{usuario, contrasena});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public List<String> obtenerTodosLosUsuarios() {
        List<String> usuarios = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_USUARIO + " FROM " + TABLE_USUARIOS, null);

        if (cursor.moveToFirst()) {
            do {
                usuarios.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return usuarios;
    }
}
