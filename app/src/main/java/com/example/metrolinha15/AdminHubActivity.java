package com.example.metrolinha15;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminHubActivity extends AppCompatActivity {

    private Button btnEstatisticas, btnListaEntrevistados, btnCadastrarEntrevistador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hub);

        btnEstatisticas = findViewById(R.id.btnEstatisticas);
        btnListaEntrevistados = findViewById(R.id.btnListaEntrevistados);
        btnCadastrarEntrevistador = findViewById(R.id.btnCadastrarEntrevistador);

        btnEstatisticas.setOnClickListener(v ->
                startActivity(new Intent(this, EstatisticasActivity.class))
        );

        btnListaEntrevistados.setOnClickListener(v ->
                startActivity(new Intent(this, ListaEntrevistadosActivity.class))
        );

        btnCadastrarEntrevistador.setOnClickListener(v ->
                startActivity(new Intent(this, CadastroEntrevistadorActivity.class))
        );
    }
}
