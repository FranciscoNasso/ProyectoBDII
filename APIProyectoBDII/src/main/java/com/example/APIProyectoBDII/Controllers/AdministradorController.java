package com.example.APIProyectoBDII.Controllers;

import com.example.APIProyectoBDII.Controllers.DTO.AdministradorDTO;
import com.example.APIProyectoBDII.Controllers.DTO.UsuarioDTO;
import com.example.APIProyectoBDII.Entities.Administradores;
import com.example.APIProyectoBDII.Entities.Usuario;
import com.example.APIProyectoBDII.Service.IAdministradorService;
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
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private IAdministradorService administradorService;

    private IUsuarioService usuarioService;

    @GetMapping("/findall")
    public ResponseEntity<?> findAllAdmin(){
        List<Administradores> administradores = administradorService.findAll();
        List<AdministradorDTO> administradorDTO = new ArrayList<>();
        List<UsuarioDTO> usuarioMostrar = new ArrayList<>();
        for (Administradores admin : administradores) {
            AdministradorDTO adminDTO = AdministradorDTO.builder()
                    .id(admin.getId())
                    .build();

            administradorDTO.add(adminDTO);
        }
        for (AdministradorDTO administrador : administradorDTO){
            Optional<Usuario> usuarioOptional = usuarioService.findById(administrador.getId());
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
    public ResponseEntity<?> getAdministradorById(@PathVariable int id){
        Optional <Administradores> administradorOptional = administradorService.findById(id);
        if (administradorOptional.isPresent()) {
            Optional<Usuario> usuarioOptional = usuarioService.findById(administradorOptional.get().getId());
            Usuario usuario = usuarioOptional.get();
            UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .apellido(usuario.getApellido())
                    .email(usuario.getEmail())
                    .build();
            return ResponseEntity.ok(usuarioDTO);
        }
        return ResponseEntity.badRequest().body("No existe el administrador con el id " + id);
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveAdministrador(@RequestBody AdministradorDTO administradorDTO) throws URISyntaxException {
        if(administradorDTO.getId() == null){
            return ResponseEntity.badRequest().body("Se requiere un id para guardar el Administrador");
        }
        Optional<Usuario> usuarioOptional = usuarioService.findById(administradorDTO.getId());
        if(usuarioOptional.isPresent()){
            administradorService.save(administradorDTO.getId());
            return ResponseEntity.created(new URI("/administrador/save")).build();
        }
        return ResponseEntity.badRequest().body("El usuario debe estar registrado previamente");
    }

    @DeleteMapping("/delete?{id}")
    public ResponseEntity<?> deleteAdministrador(@PathVariable Integer id){
        if(id != null){
            if(administradorService.findById(id).isPresent()){
                usuarioService.delete(id);
                return ResponseEntity.ok().body("Administrador eliminado");
            }
            return ResponseEntity.badRequest().body("El administrador no existe");
        }
        return ResponseEntity.badRequest().body("Debe Ingresar un Id valido");
    }


}
