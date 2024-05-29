package com.example.APIProyectoBDII.Controllers;

import com.example.APIProyectoBDII.Controllers.DTO.PartidoDTO;
import com.example.APIProyectoBDII.Entities.Partido;
import com.example.APIProyectoBDII.Service.IPaisService;
import com.example.APIProyectoBDII.Service.IPartidoService;
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
    private IPaisService paisService;

    private Integer idPartido = 1;

    @GetMapping("/findall")
    public ResponseEntity<?> getAllPartidos() {
        List<Partido> partidoList = partidoService.getPartidos();
        List<PartidoDTO> partidoDTOList = new ArrayList<>();
        for (Partido partido : partidoList) {
            PartidoDTO partidoDTo = PartidoDTO.builder()
                    .id(partido.getId())
                    .fecha(partido.getFecha())
                    .hora(partido.getHora())
                    .paisLocal(partido.getIdPaislocal().getNombre())
                    .paisVisitante(partido.getIdPaisvisitante().getNombre())
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
                    .build();
            return ResponseEntity.ok(partidoOptional);
        }
        return ResponseEntity.badRequest().body("Partido no encontrado");
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PartidoDTO partidoDTO) throws URISyntaxException {
        if(partidoDTO.getFecha() == null || partidoDTO.getHora() == null || partidoDTO.getPaisLocal().isBlank() || partidoDTO.getPaisVisitante().isBlank()){
            ResponseEntity.badRequest().body("El partido necesita fecha, hora, pais local y pais visitante");
        }
        if(paisService.findById(partidoDTO.getPaisLocal()).isPresent() && paisService.findById(partidoDTO.getPaisVisitante()).isPresent()){
            partidoService.savePartido(idPartido, partidoDTO.getFecha(), partidoDTO.getHora(), partidoDTO.getPaisLocal(), partidoDTO.getPaisVisitante());
            idPartido += 1;
            return ResponseEntity.created(new URI("/partido/save")).build();
        }
        return ResponseEntity.badRequest().body("Los paises deben estar previamente cargados");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer idPartido){
        if(idPartido != null){
            if(partidoService.getPartido(idPartido).isPresent()){
                partidoService.deletePartido(idPartido);
                return ResponseEntity.ok("Partido eliminado correctamente");
            }
            return ResponseEntity.badRequest().body("Partido con ese id no existe");
        }
        return ResponseEntity.badRequest().body("Id es requerido");
    }

}
