package persistance.entity;

import persistance.entity.enums.PegiEnum;

import java.time.LocalDate;


public class Movie {

    private int id;
    private String titulo;
    private String genero;
    private String nombreDirector;
    private LocalDate fechaLanzamiento;
    private PegiEnum clasificacionEdad;
    private int stock;

    public Movie() {
    }

    public Movie(int id, String titulo, String genero, String nombreDirector, LocalDate fechaLanzamiento, PegiEnum clasificacionEdad, int stock) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.nombreDirector = nombreDirector;
        this.fechaLanzamiento = fechaLanzamiento;
        this.clasificacionEdad = clasificacionEdad;
        this.stock = stock;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public PegiEnum getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(PegiEnum clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "stock=" + stock +
                ", clasificacionEdad=" + clasificacionEdad +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", nombreDirector='" + nombreDirector + '\'' +
                ", genero='" + genero + '\'' +
                ", titulo='" + titulo + '\'' +
                ", id=" + id +
                '}';
    }
}
