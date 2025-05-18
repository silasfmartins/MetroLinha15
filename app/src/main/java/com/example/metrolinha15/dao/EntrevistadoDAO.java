package com.example.metrolinha15.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EntrevistadoDAO {
    private BancoHelper dbHelper;

    public EntrevistadoDAO(Context context) {
        dbHelper = new BancoHelper(context);
    }

    public void inserirEntrevistado(String nome, String telefone) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("telefone", telefone);
        db.insert("Entrevistado", null, valores);
        db.close();
    }

    public int contarEntrevistados() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM Entrevistado", null);
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return total;
    }

    public List<String> getNomeEntrevistado() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Entrevistado", null);
        ArrayList<String> nomes = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                nomes.add(cursor.getString(cursor.getColumnIndexOrThrow("Nome")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nomes;
    }

    public List<String> listarEntrevistados() {
        List<String> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT nome FROM Entrevistado", null);
        while (cursor.moveToNext()) {
            String nome = cursor.getString(0);
            lista.add(nome);
        }

        cursor.close();
        db.close();
        return lista;
    }
}
