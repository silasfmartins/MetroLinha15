package com.example.metrolinha15.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class EstacaoDAO {
    private BancoHelper dbHelper;

    public EstacaoDAO(Context context) {
        dbHelper = new BancoHelper(context);
    }

    public void inserirEstacao(String nomeEstacao, int origem, int destino) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nomeEstacao", nomeEstacao);
        valores.put("origem", origem);
        valores.put("destino", destino);
        db.insert("Estacao", null, valores);
        db.close();
    }
}
