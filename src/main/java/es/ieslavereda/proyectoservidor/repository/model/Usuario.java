package es.ieslavereda.proyectoservidor.repository.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Usuario {

    private String dni;
    private String usuario;
    private String contrasenya;
    private String nombre;
    private String apellidos;
    private String email;
    private String domicilio;
    private String codigo_postal;
    private Date fecha_nacimiento;
    private String tarjeta_credito;

    @Override
    public boolean equals(Object object){
        if (object == null || !(object instanceof Usuario))
            return false;
        Usuario usuario = (Usuario) object;
        return dni == usuario.getDni();
    }

    @Override
    public int hashCode(){
        return dni.hashCode();
    }

}
