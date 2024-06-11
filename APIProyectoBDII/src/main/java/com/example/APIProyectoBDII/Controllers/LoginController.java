package com.example.APIProyectoBDII.Controllers;

import com.example.APIProyectoBDII.Controllers.DTO.LoginDTO;
import com.example.APIProyectoBDII.Controllers.DTO.LoginDTO.LoginDTOBuilder;
import com.example.APIProyectoBDII.Entities.Login;
import com.example.APIProyectoBDII.Repository.IAdministrador;
import com.example.APIProyectoBDII.Service.ILoginService;
import com.example.APIProyectoBDII.Service.IUsuarioService;
import com.example.APIProyectoBDII.Service.IAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.APIProyectoBDII.Utils.JWTUtil;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService loginService;
    @Autowired
    private IAdministradorService administradoresService;
    @Autowired
    private IUsuarioService usuarioService;

    private String hashMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
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

    @PostMapping("/find/{ci}")
    public ResponseEntity<?> findById(@PathVariable Integer ci, @RequestBody String contrasenia){
        Optional<Login> loginOptional = loginService.findById(ci);
        if(loginOptional.isPresent()){
            Login login = loginOptional.get();
            if (loginService.getContrasenia(ci).equals(hashMD5(contrasenia))) {
                int esAdmin = administradoresService.checkExistence(ci);
                LoginDTO loginDTO = LoginDTO.builder()
                        .ci(login.getId())
                        .contrasenia(hashMD5(contrasenia))
                        .build();
                String jwt = JWTUtil.generarJWT(loginDTO.getCi(), loginDTO.getContrasenia(), esAdmin != 0);
                usuarioService.setJWT(ci, jwt);
                return ResponseEntity.ok(jwt);
            } else {
                return ResponseEntity.badRequest().body("Contraseña incorrecta"+contrasenia+"------ "+loginService.getContrasenia(ci)+"<--->"+hashMD5(contrasenia));
            }
        }
        
        return ResponseEntity.badRequest().body("No existe login con esa cedula");
    }   


    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody LoginDTO loginDTO) throws URISyntaxException {
        if(loginDTO.getCi() == null || loginDTO.getContrasenia().isBlank()){
            return ResponseEntity.badRequest().body("Cedula y contraseña son requeridos");
        }
        loginService.save(loginDTO.getCi(), hashMD5(loginDTO.getContrasenia()));
        return ResponseEntity.created(new URI("/login/register")).build();
    }
}