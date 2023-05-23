package es.ieslavereda.proyecto_servidor.service;

import es.ieslavereda.proyecto_servidor.repository.UsuarioDBRepository;
import es.ieslavereda.proyecto_servidor.repository.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioDBService {

    @Autowired
    public UsuarioDBRepository repository;


    public Usuario addUsuario(Usuario usuario) throws SQLException {
        return repository.addUsuario(usuario);
    }

    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        return repository.updateUsuario(usuario);
    }

    public Usuario deleteUsuarioById(int id) throws SQLException {
        return repository.deleteUsuarioById(id);
    }

    public List<Usuario> getAllUsuariosDB() throws SQLException {
        return repository.getAllUsuarios();
    }

    public Usuario getUsuarioById(int id) throws SQLException {
        return repository.getUsuarioById(id);
    }
}
