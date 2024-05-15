package es.ieslavereda.proyectoservidor.controller;


import es.ieslavereda.proyectoservidor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


/*
@RestController
@RequestMapping("/miraveredaAPI")
public class PeliculaController {

    @Autowired
    private PeliculaService service;

    @GetMapping("/peliculas")
    public ResponseEntity<?> getAllPeliculas() {

        try {
            return new ResponseEntity<>(service.getAllPeliculas(), HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put(("message"),e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

 */
