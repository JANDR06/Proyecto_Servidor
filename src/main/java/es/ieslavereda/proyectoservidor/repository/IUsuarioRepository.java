package es.ieslavereda.proyectoservidor.repository;

import es.ieslavereda.proyectoservidor.repository.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioRepository {

    Usuario getUsuario(String dni) throws SQLException;
    Usuario addUsuario(Usuario usuario) throws SQLException;
    Usuario updateUsuario(Usuario usuario) throws SQLException;
    Usuario deleteUsuario(int id) throws SQLException;
    List<Usuario> getAllUsuarios() throws SQLException;
}
