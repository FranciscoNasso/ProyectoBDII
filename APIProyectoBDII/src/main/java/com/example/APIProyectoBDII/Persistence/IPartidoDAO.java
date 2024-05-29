package com.example.APIProyectoBDII.Persistence;

import com.example.APIProyectoBDII.Entities.Partido;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface IPartidoDAO {

    List<Partido> getPartidos();

    Optional<Partido> getPartido(Integer id);

    public void savePartido(Integer id, LocalDate fecha, LocalTime hora, String partidoLocal, String partidoVisitante);

    public void deletePartido(Integer id);
}
