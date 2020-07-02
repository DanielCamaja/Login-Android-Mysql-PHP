package com.example.loginagenda;

public class DatosAgenda {

    private int id;
    private String hora;
    private String nombre;
    private String asunto;
    private String image;

    public DatosAgenda(int id, String hora, String nombre, String asunto, String image) {
        this.id = id;
        this.hora = hora;
        this.nombre = nombre;
        this.asunto = asunto;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
