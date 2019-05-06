package com.example.plataforma.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String SPINNER_DATA = "SPINNER_DATA";
    private TextView textoActual, textoGuardado;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = findViewById(R.id.texto);
        boton = findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doColors();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Save in savedInstanceState.
        savedInstanceState.putStringArray(SPINNER_DATA, colors);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state from the savedInstanceState.
        if(savedInstanceState != null) {
            colors = savedInstanceState.getStringArray(SPINNER_DATA);
            addColors(colors);
        }
    }

    /**
     * Click button
     */
    public void doColors() {
        // super simple data for this example
        colors = new String[]{"Red", "Blue", "White", "Yellow", "Black", "Green", "Purple", "Orange", "Grey"};
        addColors(colors);
    }

    /**
     * Add data to texto
     */s
    private void addColors(String[] colorsToAdd) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colorsToAdd);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }
}