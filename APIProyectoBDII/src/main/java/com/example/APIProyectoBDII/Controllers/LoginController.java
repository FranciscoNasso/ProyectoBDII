package com.example.APIProyectoBDII.Controllers;

import com.example.APIProyectoBDII.Controllers.DTO.LoginDTO;
import com.example.APIProyectoBDII.Controllers.DTO.LoginDTO.LoginDTOBuilder;
import com.example.APIProyectoBDII.Controllers.DTO.UsuarioDTO;
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
import java.util.Map;
import java.util.Optional;

import com.example.APIProyectoBDII.Utils.JWTUtil;
import com.fasterxml.jackson.core.io.JsonStringEncoder;

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
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Login> loginList = loginService.findAll();
        List<LoginDTO> loginDTOList = new ArrayList<>();
        for (Login login : loginList) {
            LoginDTO loginDTO = LoginDTO.builder()
                    .ci(login.getId())
                    .contrasenia(login.getNombre())
                    .build();
            loginDTOList.add(loginDTO);
        }
        return ResponseEntity.ok(loginDTOList);
    }

    @PostMapping("/find/{ci}")
    public ResponseEntity<?> findById(@PathVariable Integer ci, @RequestBody String contrasenia) {
        String hashedPass = hashMD5(contrasenia);
        Optional<Login> loginOptional = loginService.findById(ci);
        if (loginOptional.isPresent()) {
            Login login = loginOptional.get();
            if (loginService.getContrasenia(ci).equals(hashedPass)) {
                int esAdmin = administradoresService.checkExistence(ci);
                LoginDTO loginDTO = LoginDTO.builder()
                        .ci(login.getId())
                        .contrasenia(hashedPass)
                        .build();
                String jwt = JWTUtil.generarJWT(loginDTO.getCi(), loginDTO.getContrasenia(), esAdmin != 0);
                usuarioService.setJWT(ci, jwt);
                return ResponseEntity.ok("{\"token\": \"" + jwt + "\"}");
            } else {
                return ResponseEntity.badRequest().body("Contraseña incorrecta" + contrasenia + "------ "
                        + loginService.getContrasenia(ci) + "<--->" + hashedPass + "<---->" + hashMD5(contrasenia));
            }
        }

        return ResponseEntity.badRequest().body("No existe login con esa cedula");
    }

    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody Map<String, String> body) throws URISyntaxException {
        // Validar los campos requeridos
        System.out.println("Entró al endpoint de registro de usuario");
        if (!body.containsKey("id") 
                || body.get("id") == null
                || !body.containsKey("password") 
                || !body.containsKey("password")
                || body.get("nombre") == null 
                || !body.containsKey("nombre")
                || body.get("apellido") == null 
                || !body.containsKey("apellido")
                || body.get("email") == null
                || !body.containsKey("email")
                || body.get("carrera") == null
                || !body.containsKey("carrera")
            ) {
            return ResponseEntity.badRequest().body("Hay datos faltantes en el registro");
        }
        System.out.println(body.toString());

        // Crear LoginDTO
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setCi(Integer.parseInt(body.get("id").trim()));
        loginDTO.setContrasenia(hashMD5(body.get("password").toString()));

        // Crear UsuarioDTO
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(Integer.parseInt(body.get("id").trim()));
        usuarioDTO.setNombre(body.get("nombre") != null ? body.get("nombre").toString() : null);
        usuarioDTO.setApellido(body.get("apellido") != null ? body.get("apellido").toString() : null);
        usuarioDTO.setEmail(body.get("email") != null ? body.get("email").toString() : null);
        usuarioDTO.setId_carrera(body.get("carrera") != null ? Integer.parseInt(body.get("carrera").trim()) : null);
        // guardar usuario en la base de datos y recien despues guardar login
        int result = usuarioService.save(usuarioDTO.getId(), usuarioDTO.getNombre(), usuarioDTO.getApellido(),
                usuarioDTO.getEmail(), usuarioDTO.getId_carrera());
        if (result == 0) {
            return ResponseEntity.badRequest().body("No se pudo registrar el usuario");
        } else {
            loginService.save(loginDTO.getCi(), loginDTO.getContrasenia());
        }
        return ResponseEntity.created(new URI("/login/register")).build();
    }
}