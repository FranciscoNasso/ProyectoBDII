package com.example.APIProyectoBDII.Controllers;

import com.example.APIProyectoBDII.Controllers.DTO.PrediccionDTO;
import com.example.APIProyectoBDII.Controllers.DTO.RankingDTO;
import com.example.APIProyectoBDII.Service.IPrediccionService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/prediccion")
public class PrediccionController {

    @Autowired
    private IPrediccionService prediccionService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PrediccionDTO prediccionDTO) throws URISyntaxException {
        if (prediccionDTO.getId_partido() == null
                || prediccionDTO.getId_participante() == null
                || prediccionDTO.getGoles_pais_local() == null
                || prediccionDTO.getGoles_pais_visitante() == null) {
            return ResponseEntity.badRequest().body("Faltan campos obligatorios");
        }
        int result = prediccionService.save(prediccionDTO.getId_partido(), prediccionDTO.getId_participante(), 
                                prediccionDTO.getGoles_pais_local(), prediccionDTO.getGoles_pais_visitante());
        if (result == 0) {
            return ResponseEntity.badRequest().body("Error en la insercion de prediccion");
        } else {
            return ResponseEntity.created(new URI("/prediccion/save")).build();
        }
    }

    @GetMapping("/getPuntaje/{id_participante}")
    public ResponseEntity<?> getPuntaje(@PathVariable Integer id_participante) {
        if (id_participante == null) {
            return ResponseEntity.badRequest().body("Faltan campos obligatorios");
        }
        int result = prediccionService.getPuntaje(id_participante);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getRanking")
    public ResponseEntity<List<RankingDTO>> getRanking() {
        List<RankingDTO> ranking = prediccionService.getRanking();
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }
}