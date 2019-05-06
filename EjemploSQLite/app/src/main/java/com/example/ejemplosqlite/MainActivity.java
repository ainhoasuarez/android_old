package com.example.ejemplosqlite;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private EditText tarea, descripcion;
    private CheckBox realizada;
    private Button botonGuardar, botonConsultar;
    private ListView listado;
    private TareaDbHelper miHelper;
    private TextView txtVacio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miHelper = new TareaDbHelper(getApplicationContext());
        tarea = findViewById(R.id.texto_nombre);
        descripcion = findViewById(R.id.texto_apellidos);
        botonGuardar = findViewById(R.id.boton);
        botonConsultar = findViewById(R.id.boton_consultar);
        realizada = findViewById(R.id.realizada);
        listado = findViewById(R.id.listado);
        txtVacio = findViewById(R.id.vacio);
        botonGuardar.setOnClickListener(this);
        botonConsultar.setOnClickListener(this);
    }

    public void insertarTarea() {
        SQLiteDatabase db = miHelper.getWritableDatabase();

        if (db != null) {
            String title = tarea.getEditableText().toString();
            String description = descripcion.getEditableText().toString();
            boolean realizada = this.realizada.isChecked();

            db.execSQL("INSERT INTO Tareas (Titulo, Realizada, Descripcion) " +
                    "VALUES ( '" + title + "', '" + realizada + "', '" + description + "');");
        } else {
            txtVacio.setText("No se ha podido encontrar la BBDD");
        }

        db.close();
        mostrarTareas();
    }

    public void mostrarTareas() {
        if (miHelper.returnAllTask() != null) {
            AdapterList adapter = new AdapterList(this, miHelper.returnAllTask());
            listado.setAdapter(adapter);
        } else {
            txtVacio.setText("La BBDD está vacía");
        }
    }

    public void actualizarTarea(Tarea tarea, String[] parametros) {
        miHelper.editTaskList(tarea, parametros);
        mostrarTareas();
    }

    public void eliminarTarea(Tarea tarea) {
        miHelper.deleteTask(tarea.getId());
        mostrarTareas();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton:
                insertarTarea();
                break;
            case R.id.boton_consultar:
                mostrarTareas();
                break;
            default:
                break;
        }
    }
}
