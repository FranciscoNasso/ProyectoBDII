package com.example.APIProyectoBDII.Controllers;

import com.example.APIProyectoBDII.Controllers.DTO.LoginDTO;
import com.example.APIProyectoBDII.Controllers.DTO.LoginDTO.LoginDTOBuilder;
import com.example.APIProyectoBDII.Entities.Login;
import com.example.APIProyectoBDII.Repository.IAdministrador;
import com.example.APIProyectoBDII.Service.ILoginService;
import com.example.APIProyectoBDII.Service.IAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService loginService;
    @Autowired
    private IAdministradorService administradoresService;

    private String hashMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<Login> loginList = loginService.findAll();
        List<LoginDTO> loginDTOList = new ArrayList<>();
        for (Login login: loginList) {
            LoginDTO loginDTO = LoginDTO.builder()
                    .ci(login.getId())
                    .contrasenia(login.getNombre())
                    .build();
            loginDTOList.add(loginDTO);
        }
        return ResponseEntity.ok(loginDTOList);
    }

    @GetMapping("/find/{ci}")
    public ResponseEntity<?> findById(@PathVariable Integer ci, @RequestParam String contrasenia){
        Optional<Login> loginOptional = loginService.findById(ci);
        if(loginOptional.isPresent()){
            Login login = loginOptional.get();
            if (loginService.getContrasenia(ci).equals(hashMD5(contrasenia))) {
                //checkear si es admin
                int esAdmin = administradoresService.checkExistence(ci);

                LoginDTO loginDTO = LoginDTO.builder()
                        .ci(login.getId())
                        .contrasenia(contrasenia)
                        .build();
                return ResponseEntity.ok(loginDTO.toString() + "\nEs admin: " + (esAdmin == 1 ? "Si" : "No"));
            } else {
                return ResponseEntity.badRequest().body("Contraseña incorrecta");
            }
        }
        
        return ResponseEntity.badRequest().body("No existe login con esa cedula");
    }

}