package es.ieslavereda.proyecto_servidor.repository;

import es.ieslavereda.proyecto_servidor.repository.model.Oficio;
import java.sql.SQLException;
import java.util.List;

public interface IOficioRepository {

    List<Oficio> getAllOficios() throws SQLException;

}
