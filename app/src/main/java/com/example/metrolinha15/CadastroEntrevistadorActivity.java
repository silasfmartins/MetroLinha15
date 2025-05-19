package com.example.metrolinha15;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.dao.EntrevistadorDAO;

public class CadastroEntrevistadorActivity extends AppCompatActivity {

    private EditText edtLogin, edtSenha;
    private EntrevistadorDAO entrevistadorDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_entrevistador);

        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        entrevistadorDAO = new EntrevistadorDAO(this);

        btnCadastrar.setOnClickListener(v -> {
            String login = edtLogin.getText().toString().trim();
            String senha = edtSenha.getText().toString().trim();

            if (login.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            entrevistadorDAO.inserirEntrevistador(login, senha);
            Toast.makeText(this, "Entrevistador cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            finish(); // ou limpar os campos
        });
    }
}
