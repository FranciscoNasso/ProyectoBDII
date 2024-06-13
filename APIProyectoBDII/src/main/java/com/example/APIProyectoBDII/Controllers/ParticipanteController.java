package com.example.APIProyectoBDII.Controllers;


import com.example.APIProyectoBDII.Controllers.DTO.AdministradorDTO;
import com.example.APIProyectoBDII.Controllers.DTO.ParticipanteDTO;
import com.example.APIProyectoBDII.Controllers.DTO.UsuarioDTO;
import com.example.APIProyectoBDII.Entities.Participante;
import com.example.APIProyectoBDII.Entities.Usuario;
import com.example.APIProyectoBDII.Service.IParticipanteService;
import com.example.APIProyectoBDII.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {

    @Autowired
    private IParticipanteService participanteService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/findall")
    public ResponseEntity<?> findAllPart(){
        List<Participante> participantes = participanteService.findAllPart();
        List<ParticipanteDTO> participanteDTO = new ArrayList<>();
        List<UsuarioDTO> usuarioMostrar = new ArrayList<>();
        for (Participante part : participantes) {
            ParticipanteDTO partDTO = ParticipanteDTO.builder()
                    .id(part.getId())
                    .build();

            participanteDTO.add(partDTO);
        }
        for (ParticipanteDTO participante : participanteDTO){
            Optional<Usuario> usuarioOptional = usuarioService.findById(participante.getId());
            Usuario usuario = usuarioOptional.get();
            UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .apellido(usuario.getApellido())
                    .email(usuario.getEmail())
                    .build();
            usuarioMostrar.add(usuarioDTO);
        }
        return ResponseEntity.ok(usuarioMostrar);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getParticipanteById(@PathVariable int id){
        Optional <Participante> participanteOptional = participanteService.findById(id);
        if (participanteOptional.isPresent()) {
            Optional<Usuario> usuarioOptional = usuarioService.findById(participanteOptional.get().getId());
            Usuario usuario = usuarioOptional.get();
            UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .apellido(usuario.getApellido())
                    .email(usuario.getEmail())
                    .build();
            return ResponseEntity.ok(usuarioDTO);
        }
        return ResponseEntity.badRequest().body("No existe el participante con el id " + id);
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveParticipante(@RequestBody ParticipanteDTO participanteDTO) throws URISyntaxException {
        if(participanteDTO.getId() == null){
            return ResponseEntity.badRequest().body("Se requiere un id para guardar el Participante");
        }
        if(usuarioService.findById(participanteDTO.getId()).isPresent()){
            participanteService.save(participanteDTO.getId(), participanteDTO.getCampeon(), participanteDTO.getSubcampeon());
            return ResponseEntity.created(new URI("/participante/save")).build();
        }
        return ResponseEntity.badRequest().body("El usuario debe estar registrado previamente");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteParticipante(@PathVariable Integer id){
        if(id != null){
            if(participanteService.findById(id).isPresent()){
                participanteService.delete(id);
                return ResponseEntity.ok().body("Participante eliminado");
            }
            return ResponseEntity.badRequest().body("El participante no existe");
        }
        return ResponseEntity.badRequest().body("Debe Ingresar un Id valido");
    }
}
