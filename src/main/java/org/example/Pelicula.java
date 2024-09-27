package org.example;

import lombok.Data;


/**
 * La clase Pelicula representa una película con sus atributos básicos.
 * Utiliza Lombok para generar automáticamente los métodos getter y setter.
 */

@Data
public class Pelicula {

    private int id;
    private String titulo;
    private int anho;
    private String director;
    private String genero;

    public Pelicula(int id, String titulo, int anho, String director, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.anho = anho;
        this.director = director;
        this.genero = genero;
    }

}
