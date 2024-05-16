package es.ieslavereda.proyectoservidor.repository;

import es.ieslavereda.proyectoservidor.repository.model.DataSource;
import es.ieslavereda.proyectoservidor.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository implements IUsuarioRepository {

    @Override
    public Usuario updateUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE cliente SET usuario = ?, contraseña = ?, nombre = ?, apellidos = ?, email = ?, domicilio = ?, codigo_postal = ?, fecha_nacimiento = ?, tarjeta_credito = ? where dniCliente = ?";
        Usuario usuario1 = getUsuario(usuario.getDni());

        if (usuario1 == null)
            return null;

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1,usuario.getUsuario());
            ps.setString(2,usuario.getContrasenya());
            ps.setString(3,usuario.getNombre());
            ps.setString(4,usuario.getApellidos());
            ps.setString(5,usuario.getEmail());
            ps.setString(6,usuario.getDomicilio());
            ps.setString(7,usuario.getCodigo_postal());
            ps.setDate(8,usuario.getFecha_nacimiento());
            ps.setString(9,usuario.getTarjeta_credito());
            ps.setString(10,usuario.getDni());

            ps.executeUpdate();

        }

        return getUsuario(usuario.getDni());
    }

    @Override
    public Usuario deleteUsuario(String dni) throws SQLException {
        Usuario usuario = getUsuario(dni);
        String query = "DELETE FROM cliente WHERE dniCliente = ?";

        if (usuario == null)
            return null;

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, dni);
            ps.executeUpdate();
        }
        return usuario;
    }

    /*
    @Override
    public List<Usuario> getAllUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "{ call obtener_usuarios() }";

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
            CallableStatement cs = connection.prepareCall(query)){

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
               usuarios.add(Usuario.builder().dni(rs.getString(1))
                       .usuario(rs.getString(2))
                       .contrasenya(rs.getString(3))
                       .nombre(rs.getString(4))
                       .apellidos(rs.getString(5))
                       .email(rs.getString(6))
                       .domicilio(rs.getString(7))
                       .codigo_postal(rs.getString(8))
                       .fecha_nacimiento(rs.getDate(9))
                       .tarjeta_credito(rs.getString(10))
                       .build());
            }
        }
        return usuarios;
    }

     */

    @Override
    public List<Usuario> getAllUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM cliente";

        try(Connection connection = DataSource.getMyOracleDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while (rs.next()) {
                usuarios.add(Usuario.builder().dni(rs.getString(1))
                        .usuario(rs.getString(2))
                        .contrasenya(rs.getString(3))
                        .nombre(rs.getString(4))
                        .apellidos(rs.getString(5))
                        .email(rs.getString(6))
                        .domicilio(rs.getString(7))
                        .codigo_postal(rs.getString(8))
                        .fecha_nacimiento(rs.getDate(9))
                        .tarjeta_credito(rs.getString(10))
                        .build());
            }
        }
        return usuarios;
    }

    @Override
    public Usuario getUsuario(String dni) throws SQLException {
        Usuario usuario = null;
        String query = "SELECT * FROM cliente WHERE dniCliente = ?";

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setString(1,dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                usuario = Usuario.builder().dni(rs.getString(1))
                        .usuario(rs.getString(2))
                        .contrasenya(rs.getString(3))
                        .nombre(rs.getString(4))
                        .apellidos(rs.getString(5))
                        .email(rs.getString(6))
                        .domicilio(rs.getString(7))
                        .codigo_postal(rs.getString(8))
                        .fecha_nacimiento(rs.getDate(9))
                        .tarjeta_credito(rs.getString(10))
                        .build();
        }
        return usuario;
    }

    @Override
    public Usuario addUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO cliente(dniCliente, usuario, contraseña, nombre, apellidos, email, domicilio, codigo_postal, fecha_nacimiento, tarjeta_credito) VALUES(?,?,?,?,?,?,?,?,?,?)";
        Usuario usuario1 = getUsuario(usuario.getDni());

        if (usuario1 != null)
            return null;

        try (Connection connection = DataSource.getMyOracleDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1,usuario.getDni());
            ps.setString(2,usuario.getUsuario());
            ps.setString(3,usuario.getContrasenya());
            ps.setString(4,usuario.getNombre());
            ps.setString(5,usuario.getApellidos());
            ps.setString(6,usuario.getEmail());
            ps.setString(7,usuario.getDomicilio());
            ps.setString(8,usuario.getCodigo_postal());
            ps.setDate(9,usuario.getFecha_nacimiento());
            ps.setString(10,usuario.getTarjeta_credito());

            ps.executeUpdate();
        }
        return usuario;
    }


}
