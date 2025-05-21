package com.example.metrolinha15;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = view.findViewById(android.R.id.text1);

                // Estilização visual
                text.setTextColor(Color.BLACK);
                text.setTextSize(16);
                text.setLineSpacing(6f, 1f);
                text.setPadding(32, 32, 32, 32);
                text.setBackgroundResource(R.drawable.bg_item_entrevistado);

                // Corrigido: aplica margem apenas se possível
                ViewGroup.LayoutParams baseParams = view.getLayoutParams();
                if (baseParams instanceof ViewGroup.MarginLayoutParams) {
                    ((ViewGroup.MarginLayoutParams) baseParams).setMargins(0, 0, 0, 24);
                    view.setLayoutParams(baseParams);
                }

                return view;
            }
        };
        listView.setAdapter(adapter);

    }
}