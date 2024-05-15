package es.ieslavereda.proyectoservidor.repository;

import es.ieslavereda.proyectoservidor.repository.model.Pelicula;
import es.ieslavereda.proyectoservidor.repository.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IPeliculaRepository {

    Pelicula getPelicula(int id) throws SQLException;
    Pelicula addPelicula(Pelicula pelicula) throws SQLException;
    Pelicula updatePelicula(Pelicula pelicula) throws SQLException;
    Pelicula deletePelicula(int id) throws SQLException;
    List<Pelicula> getAllPeliculas() throws SQLException;
}
