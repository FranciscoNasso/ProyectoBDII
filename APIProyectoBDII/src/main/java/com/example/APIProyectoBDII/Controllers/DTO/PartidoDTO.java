package com.example.APIProyectoBDII.Controllers.DTO;

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

    private String paisLocal;

    private String paisVisitante;

    private Integer goles_paisLocal;

    private Integer goles_paisVisitante;


}
