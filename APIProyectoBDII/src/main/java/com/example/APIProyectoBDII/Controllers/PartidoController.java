package com.example.APIProyectoBDII.Controllers;

import com.example.APIProyectoBDII.Controllers.DTO.PartidoDTO;
import com.example.APIProyectoBDII.Entities.Partido;
import com.example.APIProyectoBDII.Service.IPaisService;
import com.example.APIProyectoBDII.Service.IPartidoService;
import com.example.APIProyectoBDII.Service.IPrediccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partido")
public class PartidoController {

    @Autowired
    private IPartidoService partidoService;

    @Autowired
    private IPrediccionService prediccionService;

    @Autowired
    private IPaisService paisService;

    @GetMapping("/findall")
    public ResponseEntity<?> findAllPartidos() {
        List<Partido> partidoList = partidoService.findAllPartidos();
        List<PartidoDTO> partidoDTOList = new ArrayList<>();
        for (Partido partido : partidoList) {
            PartidoDTO partidoDTo = PartidoDTO.builder()
                    .id(partido.getId())
                    .fecha(partido.getFecha())
                    .hora(partido.getHora())
                    .paisLocal(partido.getIdPaislocal().getNombre())
                    .paisVisitante(partido.getIdPaisvisitante().getNombre())
                    .goles_pais_local(partido.getGoles_pais_local())
                    .goles_pais_visitante(partido.getGoles_pais_visitante())
                    .build();
            partidoDTOList.add(partidoDTo);
        }
        return ResponseEntity.ok(partidoDTOList);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getPartidoById(@PathVariable Integer id) {
        Optional<Partido> partidoOptional = partidoService.getPartido(id);
        if (partidoOptional.isPresent()) {
            Partido partido = partidoOptional.get();
            PartidoDTO partidoDTO = PartidoDTO.builder()
                    .id(partido.getId())
                    .fecha(partido.getFecha())
                    .hora(partido.getHora())
                    .paisLocal(partido.getIdPaislocal().getNombre())
                    .paisVisitante(partido.getIdPaisvisitante().getNombre())
                    .goles_pais_local(partido.getGoles_pais_local())
                    .goles_pais_visitante(partido.getGoles_pais_visitante())
                    .build();
            return ResponseEntity.ok(partidoDTO);
        }
        return ResponseEntity.badRequest().body("Partido no encontrado");
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PartidoDTO partidoDTO) throws URISyntaxException {
        if (partidoDTO.getFecha() == null || partidoDTO.getHora() == null || partidoDTO.getPaisLocal() == null
                || partidoDTO.getPaisVisitante() == null) {
            return ResponseEntity.badRequest().body("El partido necesita fecha, hora, pais local y pais visitante");
        }
        if (paisService.findById(partidoDTO.getPaisLocal()).isPresent()
                && paisService.findById(partidoDTO.getPaisVisitante()).isPresent()) {
            if (partidoDTO.getGoles_pais_local() == null) {
                partidoDTO.setGoles_pais_local(-1);
            }
            if (partidoDTO.getGoles_pais_visitante() == null) {
                partidoDTO.setGoles_pais_visitante(-1);
            }
            partidoService.savePartido(partidoDTO.getFecha(), partidoDTO.getHora(), partidoDTO.getPaisLocal(),
                    partidoDTO.getPaisVisitante(), partidoDTO.getGoles_pais_local(),
                    partidoDTO.getGoles_pais_visitante());
            return ResponseEntity.created(new URI("/partido/save")).build();
        }
        return ResponseEntity.badRequest().body("Los paises deben estar previamente cargados");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (id != null) {
            if (partidoService.getPartido(id).isPresent()) {
                partidoService.deletePartido(id);
                return ResponseEntity.ok("Partido eliminado correctamente");
            }
            return ResponseEntity.badRequest().body("Partido con ese id no existe");
        }
        return ResponseEntity.badRequest().body("Id es requerido");
    }

    @PostMapping("/load-score/{id}")
    public ResponseEntity<?> save(@PathVariable Integer id, @RequestBody PartidoDTO partidoDTO)
            throws URISyntaxException {
        Integer id_partido = partidoDTO.getId();
        Integer goles_pais_local = partidoDTO.getGoles_pais_local();
        Integer goles_pais_visitante = partidoDTO.getGoles_pais_visitante();

        if (id == null || goles_pais_local == null || goles_pais_visitante == null) {
            return ResponseEntity.badRequest().body("Faltan campos obligatorios");
        }
        int result = partidoService.loadScore(id, goles_pais_local, goles_pais_visitante);
        if (result == 0) {
            return ResponseEntity.badRequest().body("Error en la insercion de resultado");
        } else {
            // actualizar prediccion
            result = prediccionService.puntuar(id_partido, goles_pais_visitante, goles_pais_local);
            if (result == 0) {
                return ResponseEntity.badRequest()
                        .body("Error en la puntuacion de prediccion o noy hay predicciones para puntuar: " + result);
            }
            return ResponseEntity.created(new URI("/partido/load-score")).build();
        }

    }

}
