package com.example.metrolinha15;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.metrolinha15.dao.EstacaoDAO;


public class SelecionarDestinoActivity extends AppCompatActivity {

    private EstacaoDAO estacaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_destino);

        estacaoDAO = new EstacaoDAO(this);
        String origem = getIntent().getStringExtra("origem");

        setButtonListener(R.id.btnVilaPrudente, "Vila Prudente", origem);
        setButtonListener(R.id.btnOratorio, "Oratório", origem);
    }

    private void setButtonListener(int id, String destino, String origem) {
        Button button = findViewById(id);
        button.setOnClickListener(v -> {
            double latitude = 0;
            double longitude = 0;

            // Obter localização
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                Toast.makeText(this, "Permissão de localização necessária", Toast.LENGTH_SHORT).show();
                return;
            }

            Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (loc != null) {
                latitude = loc.getLatitude();
                longitude = loc.getLongitude();
            }

            // Registrar com localização
            estacaoDAO.registrar(origem, destino, latitude, longitude);
            Toast.makeText(this, "Registrado: " + origem + " → " + destino, Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
