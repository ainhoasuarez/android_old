package com.example.ejemplosqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterList extends BaseAdapter implements View.OnClickListener {
    protected MainActivity activity;
    protected ArrayList<Tarea> items;
    protected Tarea tarea;
    private EditText titulo, descripcion;
    private CheckBox realizada;
    private Button actualizar, borrar;
    private TextView id;

    public AdapterList(MainActivity activity, ArrayList<Tarea> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Tarea getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item, null);
        }
        tarea = items.get(position);
        titulo = v.findViewById(R.id.edit_tarea);
        descripcion = v.findViewById(R.id.edit_descripcion);
        realizada = v.findViewById(R.id.check_realizada);
        actualizar = v.findViewById(R.id.btn_actualizar);
        borrar = v.findViewById(R.id.btn_borrar);
        id = v.findViewById(R.id.txt_id);
        id.setText(tarea.getId() + " - ");
        titulo.setText(tarea.getTitulo());
        descripcion.setText(tarea.getDescripcion());
        realizada.setChecked(tarea.getRealizada());
        actualizar.setOnClickListener(this);
        borrar.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_actualizar:
                String[] parametros = {
                        titulo.getEditableText().toString(),
                        Boolean.toString(realizada.isChecked()),
                        descripcion.getEditableText().toString()
                };
                activity.actualizarTarea(tarea, parametros);
                break;
            case R.id.btn_borrar:
                activity.eliminarTarea(tarea);
                break;
            default:
                break;
        }
    }
}