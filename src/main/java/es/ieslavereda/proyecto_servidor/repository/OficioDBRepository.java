package es.ieslavereda.proyecto_servidor.repository;

import es.ieslavereda.proyecto_servidor.repository.model.MyDataSource;
import es.ieslavereda.proyecto_servidor.repository.model.Oficio;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OficioDBRepository implements IOficioRepository {

    @Override
    public List<Oficio> getAllOficios() throws SQLException {
        ArrayList<Oficio> oficiosDB = new ArrayList<>();
        String sql = "{call obtener_oficios(?)}";

        try(Connection con = MyDataSource.getMySQLDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)){
            cs.setNull(1,0);
            ResultSet rs = cs.executeQuery();{

                while(rs.next()){
                    oficiosDB.add(Oficio.builder().idOficio(rs.getInt(1)).descripcion(rs.getString(2))
                            .urlImagen(rs.getString(4)).build());
                }
            }
            return oficiosDB;
        }
    }




}
