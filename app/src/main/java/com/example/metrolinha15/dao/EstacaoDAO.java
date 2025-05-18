package com.example.metrolinha15.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.metrolinha15.dao.BancoHelper;

public class EstacaoDAO {

    private BancoHelper helper;

    public EstacaoDAO(Context context) {
        this.helper = new BancoHelper(context);
    }

    // Registra o par origem-destino
    public void registrar(String origem, String destino) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("origem", origem);
        values.put("destino", destino);
        db.insert("Estacao", null, values);
        db.close();
    }

    // Retorna uma string com as estatísticas de origem e destino
    public String getEstatisticas() {
        SQLiteDatabase db = helper.getReadableDatabase();
        StringBuilder sb = new StringBuilder();

        // Contar todas as ocorrências por origem
        Cursor c1 = db.rawQuery("SELECT origem, COUNT(*) as total FROM Estacao GROUP BY origem", null);
        sb.append("Totais por ORIGEM:\n");
        while (c1.moveToNext()) {
            sb.append("- ").append(c1.getString(0)).append(": ").append(c1.getInt(1)).append("\n");
        }
        c1.close();

        // Contar todas as ocorrências por destino
        Cursor c2 = db.rawQuery("SELECT destino, COUNT(*) as total FROM Estacao GROUP BY destino", null);
        sb.append("\nTotais por DESTINO:\n");
        while (c2.moveToNext()) {
            sb.append("- ").append(c2.getString(0)).append(": ").append(c2.getInt(1)).append("\n");
        }
        c2.close();

        db.close();
        return sb.toString();
    }

    // Remove todos os registros
    public void zerarTotais() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("Estacao", null, null);
        db.close();
    }
}
