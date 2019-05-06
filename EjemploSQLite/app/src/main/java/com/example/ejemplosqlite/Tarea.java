package com.example.ejemplosqlite;

public class Tarea {
    private int id;
    private String titulo;
    private Boolean realizada;
    private String descripcion;

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Boolean getRealizada() {
        return realizada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setRealizada(Boolean doneornot) {
        this.realizada = doneornot;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
