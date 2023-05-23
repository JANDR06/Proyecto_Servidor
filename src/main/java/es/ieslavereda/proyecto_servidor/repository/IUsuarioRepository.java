package es.ieslavereda.proyecto_servidor.repository;

import es.ieslavereda.proyecto_servidor.repository.model.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface IUsuarioRepository {

    // POST: CREACIÓN DE UN USUARIO EN LA BD
    Usuario addUsuario(Usuario usuario) throws SQLException;

    // PUT: ACTUALIZACIÓN DE UN USUARIO
    Usuario updateUsuario(Usuario usuario) throws SQLException;

    // DELETE: ELIMINACIÓN DE UN USUARIO MEDIANTE SU ID
    Usuario deleteUsuarioById(int id) throws SQLException;

    // GET: OBTENCIÓN DE TODOS LOS USUARIOS
    List<Usuario> getAllUsuarios() throws SQLException;

}
