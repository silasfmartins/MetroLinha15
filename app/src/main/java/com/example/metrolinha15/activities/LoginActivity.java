package com.example.metrolinha15.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.R;
import com.example.metrolinha15.dao.EntrevistadorDAO;

public class LoginActivity extends AppCompatActivity {

    private EditText etLogin, etSenha;
    private Button btnLogin;
    private EntrevistadorDAO entrevistadorDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = findViewById(R.id.etLogin);
        etSenha = findViewById(R.id.etSenha);
        btnLogin = findViewById(R.id.btnLogin);
        entrevistadorDAO = new EntrevistadorDAO(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = etLogin.getText().toString();
                String senha = etSenha.getText().toString();

                if (entrevistadorDAO.autenticar(login, senha)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
