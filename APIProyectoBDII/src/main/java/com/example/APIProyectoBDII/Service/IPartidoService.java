package com.example.APIProyectoBDII.Service;

import com.example.APIProyectoBDII.Controllers.DTO.PartidoPrediccionDTO;
import com.example.APIProyectoBDII.Entities.Partido;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface IPartidoService {

    public List<Partido> findAllPartidos();

    public Optional<Partido> getPartido(Integer id);

    public void savePartido(LocalDate fecha, LocalTime hora, String id_pais_local, String id_pais_visitante, Integer goles_pais_local, Integer goles_pais_visitante);

    public void deletePartido(Integer id);

    public int loadScore(Integer id, Integer goles_pais_local, Integer goles_pais_visitante);

    public List<PartidoPrediccionDTO> findAllPartidosById(Integer id);
}
