package com.example.APIProyectoBDII.Controllers;

import com.example.APIProyectoBDII.Controllers.DTO.UsuarioDTO;
import com.example.APIProyectoBDII.Entities.Usuario;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<Usuario> usuarioList = usuarioService.findAll();
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
        for (Usuario usuario: usuarioList) {
            UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .apellido(usuario.getApellido())
                    .email(usuario.getEmail())
                    .build();
            usuarioDTOList.add(usuarioDTO);
        }
        return ResponseEntity.ok(usuarioDTOList);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .apellido(usuario.getApellido())
                    .email(usuario.getEmail())
                    .build();
            return ResponseEntity.ok(usuarioDTO);
        }

        return ResponseEntity.badRequest().body("No existe usuario con ese Id");
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {
        if(usuarioDTO.getId() == null || usuarioDTO.getNombre().isBlank() || usuarioDTO.getApellido().isBlank() || usuarioDTO.getEmail().isBlank()){
            return ResponseEntity.badRequest().body("Name, Last Name and Email are required");
        }
        usuarioService.save(usuarioDTO.getId(), usuarioDTO.getNombre(), usuarioDTO.getApellido(), usuarioDTO.getEmail());
        return ResponseEntity.created(new URI("/usuario/save")).build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if(id!=null){
            if(usuarioService.findById(id).isPresent()){
                usuarioService.delete(id);
                return ResponseEntity.ok("Usuario eliminado");}
            return ResponseEntity.badRequest().body("Usuario no existe");
        }
       return ResponseEntity.badRequest().body("se requiere un id");
    }

}
