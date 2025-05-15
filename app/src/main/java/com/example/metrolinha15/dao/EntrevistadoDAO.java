package com.example.metrolinha15.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
}
