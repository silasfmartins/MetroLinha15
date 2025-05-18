package com.example.metrolinha15;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsuario, etSenha;
    private Button btnLoginAdmin, btnCadastroEntrevistado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etSenha = findViewById(R.id.etSenha);
        btnLoginAdmin = findViewById(R.id.btnLoginAdmin);
        btnCadastroEntrevistado = findViewById(R.id.btnCadastroEntrevistado);

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUsuario.getText().toString();
                String senha = etSenha.getText().toString();

                if (usuario.equals("admin") && senha.equals("admin")) {
                    startActivity(new Intent(LoginActivity.this, EstatisticasActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciais inválidas!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCadastroEntrevistado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CadastroEntrevistadoActivity.class));
            }
        });
    }
}
