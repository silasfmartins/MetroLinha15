package com.example.metrolinha15;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.dao.EntrevistadoDAO;

import org.w3c.dom.Text;

public class CadastroEntrevistadoActivity extends AppCompatActivity {

    private EditText etNome, etTelefone;
    private Button btnSalvar;
    private EntrevistadoDAO entrevistadoDAO;
    private TextView contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_entrevistado);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        btnSalvar = findViewById(R.id.btnSalvar);
        entrevistadoDAO = new EntrevistadoDAO(this);
        contador = findViewById(R.id.contadorEntrevistado);

        //contador.setText(entrevistadoDAO.getNomeEntrevistado());
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = etNome.getText().toString();
                String telefone = etTelefone.getText().toString();

                if (!nome.isEmpty() && !telefone.isEmpty()) {
                    entrevistadoDAO.inserirEntrevistado(nome, telefone);
                    Toast.makeText(CadastroEntrevistadoActivity.this, "Entrevistado salvo com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CadastroEntrevistadoActivity.this, "Preencha todos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
