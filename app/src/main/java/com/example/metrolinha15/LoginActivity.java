package com.example.metrolinha15;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.dao.EntrevistadorDAO;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsuario, etSenha;
    private Button btnLoginAdmin;
    private EntrevistadorDAO entrevistadorDAO;

    private ImageButton btnToggleSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etSenha = findViewById(R.id.etSenha);
        btnLoginAdmin = findViewById(R.id.btnLoginAdmin);
        entrevistadorDAO = new EntrevistadorDAO(this);
        btnToggleSenha = findViewById(R.id.btnToggleSenha);

        btnToggleSenha.setOnClickListener(v -> {
            if (etSenha.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                etSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnToggleSenha.setImageResource(R.drawable.ic_eye_open);
            } else {
                etSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnToggleSenha.setImageResource(R.drawable.ic_eye_closed);
            }
            etSenha.setSelection(etSenha.getText().length()); // mantém o cursor no final
        });

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUsuario.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();

                if (usuario.equals("admin") && senha.equals("admin")) {
                    startActivity(new Intent(LoginActivity.this, AdminHubActivity.class));
                    finish();
                } else if (entrevistadorDAO.autenticar(usuario, senha)) {
                    startActivity(new Intent(LoginActivity.this, CadastroEntrevistadoActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciais inválidas!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}