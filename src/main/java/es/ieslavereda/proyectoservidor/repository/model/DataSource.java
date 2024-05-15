package es.ieslavereda.proyectoservidor.repository.model;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.Bean;
import java.sql.SQLException;

public class DataSource {

    @Bean(name = "oracleDataSource")
    public static javax.sql.DataSource getMyOracleDataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setURL("jdbc:oracle:thin:@172.28.201.239:1521:xe");
        dataSource.setUser("C##1DAMPAUL");
        dataSource.setPassword("password");
        return dataSource;
    }

}
