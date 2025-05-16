package com.example.metrolinha15;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.R;
import com.example.metrolinha15.dao.EstacaoDAO;

import java.util.List;

public class RegistroOrigemDestinoActivity extends AppCompatActivity {

    private Spinner spOrigem, spDestino;
    private Button btnRegistrar;
    private EstacaoDAO estacaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_origem_destino);

        spOrigem = findViewById(R.id.spOrigem);
        spDestino = findViewById(R.id.spDestino);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        estacaoDAO = new EstacaoDAO(this);

        carregarEstacoes();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String origem = spOrigem.getSelectedItem().toString();
                String destino = spDestino.getSelectedItem().toString();

                if (!origem.equals(destino)) {
                    estacaoDAO.registrarOrigem(origem);
                    estacaoDAO.registrarDestino(destino);
                    Toast.makeText(RegistroOrigemDestinoActivity.this, "Registro salvo!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegistroOrigemDestinoActivity.this, "Origem e destino não podem ser iguais!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void carregarEstacoes() {
        List<String> estacoes = estacaoDAO.obterNomeEstacao(1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, estacoes
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrigem.setAdapter(adapter);
        spDestino.setAdapter(adapter);
    }
}