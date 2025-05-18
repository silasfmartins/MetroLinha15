package com.example.metrolinha15;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelecionarOrigemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_origem);

        setButtonListener(R.id.btnVilaPrudente, "Vila Prudente");
        setButtonListener(R.id.btnOratorio, "Oratório");
        // Adicione mais botões se necessário
    }

    private void setButtonListener(int id, String estacao) {
        Button button = findViewById(id);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, SelecionarDestinoActivity.class);
            intent.putExtra("origem", estacao);
            startActivity(intent);
        });
    }
}
