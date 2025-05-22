package com.example.metrolinha15;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.dao.EstacaoDAO;

public class RegistroDestinoActivity extends AppCompatActivity {
    private GridLayout gridLinha12, gridLinha15;
    private Button btnRegistrar;
    private String origem, destinoSelecionado = null;
    private EstacaoDAO estacaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_destino);

        gridLinha12 = findViewById(R.id.gridLinha12);
        gridLinha15 = findViewById(R.id.gridLinha15);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        estacaoDAO = new EstacaoDAO(this);
        // Recupera a origem passada pela intent
        origem = getIntent().getStringExtra("origem");

        // Popula os botões com base nos arrays
        String[] estacoes12 = getResources().getStringArray(R.array.estacoes2); // Linha 12
        String[] estacoes15 = getResources().getStringArray(R.array.estacoes);  // Linha 15

        adicionarBotoesEstacoes(gridLinha12, getResources().getStringArray(R.array.estacoes2), R.drawable.botao_linha12);
        adicionarBotoesEstacoes(gridLinha15, getResources().getStringArray(R.array.estacoes), R.drawable.botao_linha15);

        btnRegistrar.setOnClickListener(v -> {
            if (destinoSelecionado != null && origem != null) {
                estacaoDAO.registrar(origem, destinoSelecionado);
                Toast.makeText(this, "Origem e destino registrados com sucesso!", Toast.LENGTH_SHORT).show();
                finish(); // ou redirecionar para outra activity
            } else {
                Toast.makeText(this, "Selecione o destino", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void adicionarBotoesEstacoes(GridLayout gridLayout, String[] estacoes, int backgroundDrawable) {
        for (String estacao : estacoes) {
            Button btn = new Button(this);
            btn.setText(estacao);
            btn.setTextColor(Color.WHITE);
            btn.setBackgroundResource(backgroundDrawable);

            btn.setPadding(24, 16, 24, 16);
            btn.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            btn.setSingleLine(false);
            btn.setEllipsize(null);
            btn.setMaxLines(2);
            btn.setMinWidth(0);
            btn.setMinimumWidth(0);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(12, 12, 12, 12);
            btn.setLayoutParams(params);

            btn.setOnClickListener(view -> {
                resetarSelecao(gridLinha12); // reseta todos da Linha 12
                resetarSelecao(gridLinha15); // reseta todos da Linha 15
                btn.setSelected(true);       // seleciona apenas esse

                // Armazena o botão selecionado
                destinoSelecionado = estacao; // ou origemSelecionada
            });

            gridLayout.addView(btn);
        }
    }

    private void resetarSelecao(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View v = gridLayout.getChildAt(i);
            if (v instanceof Button) {
                v.setSelected(false);
            }
        }
    }
}