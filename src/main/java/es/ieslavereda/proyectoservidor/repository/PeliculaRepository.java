package es.ieslavereda.proyectoservidor.repository;

import es.ieslavereda.proyectoservidor.repository.model.DataSource;
import es.ieslavereda.proyectoservidor.repository.model.Pelicula;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PeliculaRepository implements IPeliculaRepository {


    @Override
    public Pelicula getPelicula(int id) throws SQLException {
        return null;
    }

    @Override
    public Pelicula addPelicula(Pelicula pelicula) throws SQLException {
        return null;
    }

    @Override
    public Pelicula updatePelicula(Pelicula pelicula) throws SQLException {
        return null;
    }

    @Override
    public Pelicula deletePelicula(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Pelicula> getAllPeliculas() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        String query = "SELECT idcontenido,TIPO, TITULO, IDIOMA, GENERO, FECHA_ESTRENO, DESCRIPCION, DIRECTOR, ACTORES, DURACION, VALORACION_MEDIA  FROM contenido WHERE EXISTS(SELECT idcontenido from pelicula)";

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while (rs.next()) {
                peliculas.add(Pelicula.builder().id(rs.getInt(1))
                                .tipo(rs.getString(2))
                                .titulo(rs.getString(3))
                                .idioma(rs.getString(4))
                                .genero(rs.getString(5))
                                .fecha_estreno(rs.getDate(6))
                                .descripcion(rs.getString(7))
                                .director(rs.getString(8))
                                .actores(rs.getString(9))
                                .duracion(rs.getInt(10))
                                .valoracion_media(rs.getDouble(11))
                                .build());
            }
        }
        return peliculas;
    }
}


