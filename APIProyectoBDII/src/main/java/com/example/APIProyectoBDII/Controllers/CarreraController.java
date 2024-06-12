package com.example.APIProyectoBDII.Controllers;

import com.example.APIProyectoBDII.Controllers.DTO.CarreraDTO;
import com.example.APIProyectoBDII.Entities.Carrera;
import com.example.APIProyectoBDII.Service.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private ICarreraService carreraService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllCarrera(){
        List<Carrera> carreraes = carreraService.findAllCarrera();
        List<CarreraDTO> carreraDTO = new ArrayList<>();
        for (Carrera carrera : carreraes) {
            CarreraDTO carreraDTO1 = CarreraDTO.builder()
                    .id(carrera.getId())
                    .nombre(carrera.getNombre())
                    .build();
            carreraDTO.add(carreraDTO1);
        }
        return ResponseEntity.ok(carreraDTO);
    }
}
