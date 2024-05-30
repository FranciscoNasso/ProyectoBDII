package com.example.APIProyectoBDII.Service;

import com.example.APIProyectoBDII.Entities.Partido;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface IPartidoService {

    public List<Partido> findAllPartidos();

    public Optional<Partido> getPartido(Integer id);

    public void savePartido(Integer id, LocalDate fecha, LocalTime hora, String paisLocal, String paisVisitante, Integer goles_paisLocal, Integer goles_paisVisitante);

    public void deletePartido(Integer id);
}
