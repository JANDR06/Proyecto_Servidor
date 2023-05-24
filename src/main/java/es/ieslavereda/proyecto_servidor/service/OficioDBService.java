package es.ieslavereda.proyecto_servidor.service;

import es.ieslavereda.proyecto_servidor.repository.OficioDBRepository;
import es.ieslavereda.proyecto_servidor.repository.model.Oficio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class OficioDBService {

    @Autowired
    public OficioDBRepository repository;

    public List<Oficio> getAllOficios() throws SQLException {
        return repository.getAllOficios();
    }

    public String getImageOficio() throws SQLException {
        return repository.getImageOficio();
    }
}
