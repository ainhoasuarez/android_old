package com.example.intentexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Actividad2 extends Activity {
    private TextView pelicula, serie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        Bundle extras = getIntent().getExtras();
        String peliculaElegida = extras.getString("pelicula_preferida");
        String serieElegida = extras.getString("serie_preferida");
        pelicula = findViewById(R.id.resultado_pelicula);
        serie = findViewById(R.id.resultado_serie);
        pelicula.setText(peliculaElegida);
        serie.setText(serieElegida);
    }

}
