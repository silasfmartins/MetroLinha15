package com.example.metrolinha15.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public void registrarOrigem(String nomeEstacao) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE Estacao SET origem = origem + 1 WHERE nomeEstacao = ?", new String[]{nomeEstacao});
        db.close();
    }

    // Incrementa o contador de destino da estação selecionada
    public void registrarDestino(String nomeEstacao) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE Estacao SET destino = destino + 1 WHERE nomeEstacao = ?", new String[]{nomeEstacao});
        db.close();
    }

    public String obterNomeEstacao(int idEstacao) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String nomeEstacao = null;

        Cursor cursor = db.rawQuery("SELECT nomeEstacao FROM Estacao WHERE idEstacao = ?", new String[]{String.valueOf(idEstacao)});
        if (cursor.moveToFirst()) {
            nomeEstacao = cursor.getString(cursor.getColumnIndexOrThrow("nomeEstacao"));
        }

        cursor.close();
        db.close();

        return nomeEstacao;
    }
}
