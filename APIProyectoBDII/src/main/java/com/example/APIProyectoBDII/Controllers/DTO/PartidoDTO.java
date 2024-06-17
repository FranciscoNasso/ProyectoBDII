package com.example.APIProyectoBDII.Controllers.DTO;

import com.example.APIProyectoBDII.Entities.Pais;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartidoDTO {

    private Integer id;

    private LocalDate fecha;

    private LocalTime hora;
    // seguramente tenga que ser cambiado a snake_case
    private String paisLocal;

    private String paisVisitante;

    private Integer goles_pais_local;

    private Integer goles_pais_visitante;


}
