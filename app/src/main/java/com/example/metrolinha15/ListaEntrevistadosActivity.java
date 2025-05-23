package com.example.metrolinha15;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.dao.EstacaoDAO;
import com.example.metrolinha15.model.Estacao; // Você precisa ter essa classe Estacao

import java.util.ArrayList;
import java.util.List;

public class ListaEntrevistadosActivity extends AppCompatActivity {

    private ListView listView;
    private EstacaoDAO estacaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_entrevistados);

        listView = findViewById(R.id.listView);
        estacaoDAO = new EstacaoDAO(this);

        // Busca todas as estações registradas (com origem, destino e localização)
        List<Estacao> estacoes = estacaoDAO.listarEstacoes();
        if (estacoes == null) {
            estacoes = new ArrayList<>();
        }

        // Converte os dados em strings formatadas
        List<String> listaFormatada = new ArrayList<>();
        for (Estacao e : estacoes) {
            String item = "Origem: " + e.getOrigem() +
                    "\nDestino: " + e.getDestino() +
                    "\nLocalização: " + e.getLatitude() + ", " + e.getLongitude();
            listaFormatada.add(item);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaFormatada) {
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

                // Aplica margem
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