package es.ieslavereda.proyectoservidor.service;

import es.ieslavereda.proyectoservidor.repository.UsuarioRepository;
import es.ieslavereda.proyectoservidor.repository.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;


    public List<Usuario> getAllUsuarios() throws SQLException {
        return repository.getAllUsuarios();
    }

    public Usuario getUsuario(String dni) throws SQLException {
        return repository.getUsuario(dni);
    }

}