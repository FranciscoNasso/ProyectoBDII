package com.example.APIProyectoBDII.Controllers;

import com.example.APIProyectoBDII.Controllers.DTO.PaisDTO;
import com.example.APIProyectoBDII.Entities.Pais;
import com.example.APIProyectoBDII.Service.IPaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    private IPaisService paisService;

    @GetMapping("/findall")
    public ResponseEntity<?> findAllPais(){
        List<Pais> paises = paisService.findAllPais();
        List<PaisDTO> paisDTO = new ArrayList<>();
        for (Pais pais : paises) {
            PaisDTO paisDTO1 = PaisDTO.builder()
                    .nombre(pais.getNombre())
                    .build();
            paisDTO.add(paisDTO1);
        }
        return ResponseEntity.ok(paisDTO);
    }

    @GetMapping("/find/{nombre}")
    public ResponseEntity<?> findPaisByName(@PathVariable String nombre){
        Optional<Pais> paisOptional = paisService.findById(nombre);
        if(paisOptional.isPresent()){
            Pais pais = paisOptional.get();
            PaisDTO paisDTO = PaisDTO.builder()
                    .nombre(pais.getNombre())
                    .build();
            return ResponseEntity.ok(paisDTO);
        }
        return ResponseEntity.badRequest().body("No existe pais con ese nombre");
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PaisDTO paisDTO) throws URISyntaxException {
        if(paisDTO.getNombre().isBlank()){
            return ResponseEntity.badRequest().body("Nombre es requerido");
        }
        paisService.save(paisDTO.getNombre(), paisDTO.getIso());
        return ResponseEntity.created(new URI("/pais/save")).build();
    }

    @DeleteMapping("/delete/{nombre}")
    public ResponseEntity<?> delete(@PathVariable String nombre){
       if(nombre.isBlank()){
           return ResponseEntity.badRequest().body("Nombre es requerido");
       }
       if(paisService.findById(nombre).isPresent()) {
           paisService.delete(nombre);
           return ResponseEntity.ok("Pais eliminado");
       }
       return ResponseEntity.badRequest().body("No existe pais con ese nombre");
    }
}
