package es.ieslavereda.proyecto_servidor.repository;

import es.ieslavereda.proyecto_servidor.repository.model.MyDataSource;
import es.ieslavereda.proyecto_servidor.repository.model.Usuario;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDBRepository implements IUsuarioRepository {


    @Override
    public Usuario addUsuario(Usuario usuario) throws SQLException {

        String query = " { call crear_usuario(?,?,?,?,?)} ";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(query)
        ) {
            cs.registerOutParameter(1, usuario.getIdUsuario());
            cs.setInt(2, usuario.getIdUsuario());
            cs.setString(3, usuario.getNombre());
            cs.setString(4, usuario.getApellidos());
            cs.setInt(5, usuario.getIdOficio());
            cs.executeUpdate();

            return usuario;
        }
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) throws SQLException {

        String query = " {? = call actualizar_usuario(?,?,?,?)}";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = connection.prepareCall(query)
        ) {
            cs.setInt(2, usuario.getIdUsuario());
            cs.setString(3, usuario.getNombre());
            cs.setString(4, usuario.getApellidos());
            cs.setInt(5, usuario.getIdOficio());
            cs.execute();
            int borrados = cs.getInt(1);
            System.out.println(borrados);
        }
        return usuario;
    }

    @Override
    public Usuario deleteUsuarioById(int id) throws SQLException {

        Usuario usuario = getUsuarioById(id);
        String sql = " {? = call eliminar_usuario(?)}";

        try (Connection con = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(2, id);
            cs.execute();
            int borrados = cs.getInt(1);
            System.out.println(borrados);

        }
        return usuario;
    }

    @Override
    public List<Usuario> getAllUsuarios() throws SQLException {
        ArrayList<Usuario> usuariosDB = new ArrayList<>();
        String query = " { call obtener_usuarios()} ";

        try(Connection connection = MyDataSource.getMySQLDataSource().getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query)){

            while(rs.next()){
                usuariosDB.add(Usuario.builder().idUsuario(rs.getInt(1)).nombre(rs.getString(2))
                        .apellidos(rs.getString(3)).idOficio(rs.getInt(4)).build());
            }
        }

        return usuariosDB;
    }

    @Override
    public Usuario getUsuarioById(int id) throws SQLException {
        Usuario usuario;
        String sql = "SELECT * FROM Usuario where idUsuario = (?)";

        try (Connection con = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            rs.next();
            usuario = Usuario.builder().idUsuario(rs.getInt(1)).nombre(rs.getString(2)).apellidos(rs.getString(3)).build();
        }
        return usuario;
    }

}
