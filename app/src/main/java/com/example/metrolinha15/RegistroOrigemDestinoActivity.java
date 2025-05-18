package com.example.metrolinha15;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.dao.EstacaoDAO;

public class RegistroOrigemDestinoActivity extends AppCompatActivity {

    private GridLayout gridOrigem, gridDestino;
    private Button btnRegistrar;
    private String origemSelecionada = null, destinoSelecionada = null;
    private EstacaoDAO estacaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_origem_destino);

        gridOrigem = findViewById(R.id.gridOrigem);
        gridDestino = findViewById(R.id.gridDestino);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        estacaoDAO = new EstacaoDAO(this);

        String[] estacoes = getResources().getStringArray(R.array.estacoes);
        adicionarBotoesEstacoes(gridOrigem, estacoes, true);
        adicionarBotoesEstacoes(gridDestino, estacoes, false);

        btnRegistrar.setOnClickListener(view -> {
            if (origemSelecionada != null && destinoSelecionada != null) {
                estacaoDAO.registrar(origemSelecionada, destinoSelecionada);
                Toast.makeText(this, "Origem e destino registrados", Toast.LENGTH_SHORT).show();
                // Redirecionar para próxima atividade ou finalizar
            } else {
                Toast.makeText(this, "Selecione origem e destino", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void adicionarBotoesEstacoes(GridLayout gridLayout, String[] estacoes, boolean isOrigem) {
        for (String estacao : estacoes) {
            Button btn = new Button(this);
            btn.setText(estacao);
            btn.setOnClickListener(view -> {
                if (isOrigem) {
                    origemSelecionada = estacao;
                    // Atualizar UI para indicar seleção
                } else {
                    destinoSelecionada = estacao;
                    // Atualizar UI para indicar seleção
                }
            });
            gridLayout.addView(btn);
        }
    }
}
