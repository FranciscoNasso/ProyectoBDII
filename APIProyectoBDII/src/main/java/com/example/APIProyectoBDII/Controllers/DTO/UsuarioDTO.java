package com.example.APIProyectoBDII.Controllers.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private Integer id_carrera;
}
