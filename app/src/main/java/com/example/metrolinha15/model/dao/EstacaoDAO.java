package com.example.metrolinha15.model.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.metrolinha15.model.Estacao;

import java.util.ArrayList;
import java.util.List;

public class EstacaoDAO {

    private BancoHelper helper;

    public EstacaoDAO(Context context) {
        this.helper = new BancoHelper(context);
    }

    // Registra o par origem-destino
    public void registrar(String origem, String destino, double latitude, double longitude) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("origem", origem);
        values.put("destino", destino);
        values.put("latitude", latitude);
        values.put("longitude", longitude);
        db.insert("Estacao", null, values);
    }

    public List<Estacao> listarEstacoes() {
        List<Estacao> lista = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Estacao", null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") String origem = cursor.getString(cursor.getColumnIndex("origem"));
            @SuppressLint("Range") String destino = cursor.getString(cursor.getColumnIndex("destino"));
            @SuppressLint("Range") double latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
            @SuppressLint("Range") double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));

            lista.add(new Estacao(origem, destino, latitude, longitude));
        }

        cursor.close();
        return lista;
    }



    // Retorna uma string com as estatísticas de origem e destino
    public String getEstatisticas() {
        SQLiteDatabase db = helper.getReadableDatabase();
        StringBuilder sb = new StringBuilder();

        // Total geral
        Cursor cTotal = db.rawQuery("SELECT COUNT(*) FROM Estacao", null);
        int totalRespostas = 0;
        if (cTotal.moveToFirst()) {
            totalRespostas = cTotal.getInt(0);
        }
        cTotal.close();

        sb.append("Total de Respostas: ").append(totalRespostas).append("\n\n");

        // RF04 - Por ORIGEM
        sb.append("RF04 - Respostas por ORIGEM:\n");
        Cursor cOrigem = db.rawQuery("SELECT origem, COUNT(*) FROM Estacao GROUP BY origem", null);
        while (cOrigem.moveToNext()) {
            String origem = cOrigem.getString(0);
            int count = cOrigem.getInt(1);
            double percentual = (count * 100.0) / totalRespostas;
            sb.append("- ").append(origem).append(": ")
                    .append(count).append(" (")
                    .append(String.format("%.1f", percentual)).append("%)\n");
        }
        cOrigem.close();

        sb.append("\n");

        // RF05 - Por DESTINO
        sb.append("RF05 - Respostas por DESTINO:\n");
        Cursor cDestino = db.rawQuery("SELECT destino, COUNT(*) FROM Estacao GROUP BY destino", null);
        while (cDestino.moveToNext()) {
            String destino = cDestino.getString(0);
            int count = cDestino.getInt(1);
            double percentual = (count * 100.0) / totalRespostas;
            sb.append("- ").append(destino).append(": ")
                    .append(count).append(" (")
                    .append(String.format("%.1f", percentual)).append("%)\n");
        }
        cDestino.close();

        sb.append("\n");

        // RF06 - Por ORIGEM e DESTINO
        sb.append("RF06 - Respostas por ORIGEM e DESTINO:\n");
        Cursor cAmbos = db.rawQuery("SELECT origem, destino, COUNT(*) FROM Estacao GROUP BY origem, destino", null);
        while (cAmbos.moveToNext()) {
            String origem = cAmbos.getString(0);
            String destino = cAmbos.getString(1);
            int count = cAmbos.getInt(2);
            double percentual = (count * 100.0) / totalRespostas;
            sb.append("- ").append(origem).append(" → ").append(destino).append(": ")
                    .append(count).append(" (")
                    .append(String.format("%.1f", percentual)).append("%)\n");
        }
        cAmbos.close();

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
