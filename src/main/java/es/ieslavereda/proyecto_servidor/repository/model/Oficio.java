package es.ieslavereda.proyecto_servidor.repository.model;


import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Oficio {

    private int idOficio;
    private String descripcion;
    private String urlImagen;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oficio oficio = (Oficio) o;
        return idOficio == oficio.idOficio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOficio);
    }
}
