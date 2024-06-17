package com.example.APIProyectoBDII.Controllers.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrediccionDTO {
    private Integer id;
    private Integer id_partido;
    private Integer id_participante;
    private Integer goles_pais_local;
    private Integer goles_pais_visitante;
    private Integer puntos;
}
