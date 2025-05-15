package com.example.metrolinha15.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.R;
import com.example.metrolinha15.dao.EntrevistadoDAO;

public class CadastroEntrevistadoActivity extends AppCompatActivity {

    private EditText etNome, etTelefone;
    private Button btnSalvar;
    private EntrevistadoDAO entrevistadoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_entrevistado);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        btnSalvar = findViewById(R.id.btnSalvar);
        entrevistadoDAO = new EntrevistadoDAO(this);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = etNome.getText().toString();
                String telefone = etTelefone.getText().toString();

                if (!nome.isEmpty() && !telefone.isEmpty()) {
                    entrevistadoDAO.inserirEntrevistado(nome, telefone);
                    Toast.makeText(CadastroEntrevistadoActivity.this, "Entrevistado salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CadastroEntrevistadoActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
