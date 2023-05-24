package es.ieslavereda.proyecto_servidor.controller;

import es.ieslavereda.proyecto_servidor.repository.model.Usuario;
import es.ieslavereda.proyecto_servidor.service.UsuarioDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/apidb")
public class UsuarioDBController {

    @Autowired
    private UsuarioDBService service;

    @GetMapping("/usuarios/")
    public ResponseEntity<?> getAllUsuarios(){
        try {
            return new ResponseEntity<>(service.getAllUsuariosDB(), HttpStatus.OK);

        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/usuarios/")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(service.addUsuario(usuario), HttpStatus.OK);

        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuarioById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.deleteUsuarioById(id), HttpStatus.OK);

        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/usuarios/")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario) throws SQLException {
        try {
            return new ResponseEntity<>(service.updateUsuario(usuario), HttpStatus.OK);

        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) throws SQLException {
        try {
            return new ResponseEntity<>(service.getUsuarioById(id), HttpStatus.OK);

        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

