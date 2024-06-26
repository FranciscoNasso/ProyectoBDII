package com.example.APIProyectoBDII.Controllers.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public interface PartidoPrediccionDTO {

    Integer getId();

    LocalDate getFecha();

    LocalTime getHora();
    
    String getId_pais_local();

    String getId_pais_visitante();

    Integer getGoles_pais_local();

    Integer getGoles_pais_visitante();

    Integer getPrediccion_pais_local();

    Integer getPrediccion_pais_visitante();
}