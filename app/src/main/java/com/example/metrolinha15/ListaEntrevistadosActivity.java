package com.example.metrolinha15;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.dao.EntrevistadoDAO;

import java.util.List;

public class ListaEntrevistadosActivity extends AppCompatActivity {

    private ListView listView;
    private EntrevistadoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_entrevistados);

        listView = findViewById(R.id.listView);
        dao = new EntrevistadoDAO(this);

        List<String> lista = dao.listarEntrevistados();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);
    }
}
