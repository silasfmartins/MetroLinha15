package com.example.metrolinha15;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.dao.BancoHelper;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastroEntrevistado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCadastroEntrevistado = findViewById(R.id.btnCadastroEntrevistado);
        btnCadastroEntrevistado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistroOrigemDestinoActivity.class);
                startActivity(intent);
            }
        });
    }
}
