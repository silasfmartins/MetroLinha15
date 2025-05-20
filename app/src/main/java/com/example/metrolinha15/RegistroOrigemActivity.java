package com.example.metrolinha15;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class RegistroOrigemActivity extends AppCompatActivity {
    TextView teste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_origem);

        teste = findViewById(R.id.TESTE);
        String origem = getIntent().getStringExtra("origem");

        teste.setText(origem);
    }
}
