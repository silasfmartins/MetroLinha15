package com.example.metrolinha15;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metrolinha15.dao.EstacaoDAO;

public class EstatisticasActivity extends AppCompatActivity {
    private EstacaoDAO estacaoDAO;
    private TextView tvResumo;
    private Button btnZerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);

        estacaoDAO = new EstacaoDAO(this);
        tvResumo = findViewById(R.id.tvResumo);
        btnZerar = findViewById(R.id.btnZerar);

        tvResumo.setText(estacaoDAO.getEstatisticas());

        btnZerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estacaoDAO.zerarTotais();
                tvResumo.setText(estacaoDAO.getEstatisticas());
            }
        });
    }
}
