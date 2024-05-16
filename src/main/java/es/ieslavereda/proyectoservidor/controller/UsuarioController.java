package es.ieslavereda.proyectoservidor.controller;

import es.ieslavereda.proyectoservidor.repository.model.Usuario;
import es.ieslavereda.proyectoservidor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/miraveredaAPI")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllUsuarios() {
        try {
            return new ResponseEntity<>(service.getAllUsuarios(), HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuarios/{dni}")
    public ResponseEntity<?> getByID(@PathVariable("dni") String dni){
        try {
            Usuario usuario = service.getUsuario(dni);
            if (usuario == null)
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(usuario, HttpStatus.OK);

        } catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/usuarios/{dni}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("dni") String dni) {
        try {
            Usuario usuario = service.deleteUsuario(dni);
            if (usuario == null)
                return new ResponseEntity<>("Usuario no encontrado",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(usuario,HttpStatus.OK);

        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuario1 = service.addUsuario(usuario);
            if (usuario1 == null)
                return new ResponseEntity<>("El usuario ya existe",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(usuario1,HttpStatus.OK);

        }  catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/usuarios")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario) {
        try{
            Usuario usuario1 = service.updateUsuario(usuario);
            if (usuario1 == null)
                return new ResponseEntity<>("Usuario no encontrado",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(usuario1,HttpStatus.OK);

        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}