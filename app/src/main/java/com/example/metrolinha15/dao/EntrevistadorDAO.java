package com.example.metrolinha15.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EntrevistadorDAO {
    private BancoHelper dbHelper;

    public EntrevistadorDAO(Context context) {
        dbHelper = new BancoHelper(context);
    }

    public void inserirEntrevistador(String login, String senha) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("login", login);
        valores.put("senha", senha);
        db.insert("Entrevistador", null, valores);
        db.close();
    }

    public boolean autenticar(String login, String senha) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] args = {login, senha};
        Cursor cursor = db.rawQuery("SELECT * FROM Entrevistador WHERE login=? AND senha=?", args);
        boolean autenticado = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return autenticado;
    }
}
