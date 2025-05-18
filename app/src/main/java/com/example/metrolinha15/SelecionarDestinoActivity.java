package com.example.metrolinha15;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.dao.EstacaoDAO;

public class SelecionarDestinoActivity extends AppCompatActivity {

    private EstacaoDAO estacaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_destino);

        estacaoDAO = new EstacaoDAO(this);
        String origem = getIntent().getStringExtra("origem");

        setButtonListener(R.id.btnVilaPrudente, "Vila Prudente", origem);
        setButtonListener(R.id.btnOratorio, "Oratório", origem);
    }

    private void setButtonListener(int id, String destino, String origem) {
        Button button = findViewById(id);
        button.setOnClickListener(v -> {
            estacaoDAO.registrar(origem, destino);
            Toast.makeText(this, "Registrado: " + origem + " → " + destino, Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
