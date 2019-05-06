package com.example.intentexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText pelicula, serie;
    private Button btnAceptar, btnNavegador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAceptar = findViewById(R.id.boton_aceptar);
        (btnAceptar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pelicula = findViewById(R.id.pelicula_preferida);
                serie = findViewById(R.id.serie_prefereida);

                Intent miIntent = new Intent(MainActivity.this, Actividad2.class);
                miIntent.putExtra("pelicula_preferida", pelicula.getText().toString());
                miIntent.putExtra("serie_preferida", serie.getText().toString());
                startActivity(miIntent);
            }
        });

        btnNavegador = findViewById(R.id.boton_navegador);
        btnNavegador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iceditorial.com/"));
                startActivity(intent);
            }
        });
    }
}
