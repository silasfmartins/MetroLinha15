package com.example.metrolinha15.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "metroLinha15";
    private static final int VERSAO = 2; // ou 3, 4 etc., desde que seja maior que antes

    public BancoHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Entrevistado (" +
                "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "telefone TEXT)");

        String sql = "CREATE TABLE Estacao (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "origem TEXT," +
                "destino TEXT," +
                "latitude REAL," +
                "longitude REAL)";
        db.execSQL(sql);

        db.execSQL("CREATE TABLE Entrevistador (" +
                "idEntrevistador INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "login TEXT NOT NULL, " +
                "senha TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Entrevistado");
        db.execSQL("DROP TABLE IF EXISTS Estacao");
        db.execSQL("DROP TABLE IF EXISTS Entrevistador");
        onCreate(db);
    }
}
