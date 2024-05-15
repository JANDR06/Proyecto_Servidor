package es.ieslavereda.proyectoservidor.repository.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Pelicula {

    private String id;
    private String tipo;
    private String titulo;
    private String idioma;
    private String genero;
    private Date fecha_estreno;
    private String descripcion;
    private String director;
    private String actores;
    private int duracion;
    private double valoracion_media;

    @Override
    public boolean equals(Object object){
        if (object == null || !(object instanceof Pelicula))
            return false;
        Pelicula pelicula = (Pelicula) object;
        return id == pelicula.getId();
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }

}

