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
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, usuario.getIdUsuario());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellidos());
            preparedStatement.executeUpdate();

            return usuario;
        }
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) throws SQLException {

        String query = " {? = call actualizar_usuario(?,?,?,?)}";

        try (Connection connection = MyDataSource.getMySQLDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidos());
            preparedStatement.setInt(3,usuario.getIdUsuario());

            preparedStatement.executeUpdate();
            return usuario;
        }
    }

    @Override
    public Usuario deleteUsuarioById(int id) throws SQLException {

        //Usuario usuario --> has de crear-te un mètode per a retornar usuari by Id;
        Usuario usuario = getUsuarioById(id);

        //usuario = Usuario.builder().idUsuario(rs.getInt(1)).nombre(rs.getString(2)).apellidos(rs.getString(3)).build();


        String sql = " {? = call eliminar_usuario(?)}";

        try (Connection con = MyDataSource.getMySQLDataSource().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setInt(2, id);
            cs.execute();

            int resultado = cs.getInt(1);
            if (resultado == 1) {
                return usuario;
            } else {
                throw new SQLException("No se pudo eliminar el usuario");
            }
        }


    }

    @Override
    public List<Usuario> getAllUsuarios() throws SQLException {
        ArrayList<Usuario> usuariosDB = new ArrayList<>();
        String query = " { call obtener_usuarios} ";

        try(Connection connection = MyDataSource.getMySQLDataSource().getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query)){

            while(rs.next()){
                usuariosDB.add(Usuario.builder().idUsuario(rs.getInt(1)).nombre(rs.getString(2)).apellidos(rs.getString(3)).build());
            }
        }

        return usuariosDB;
    }

    @Override
    public Usuario getUsuarioById(int id) throws SQLException {
        Usuario usuario;
        String sql = "SELECT * FROM usuario where id = " +id;

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
