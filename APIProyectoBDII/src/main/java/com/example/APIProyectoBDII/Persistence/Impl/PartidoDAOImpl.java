package com.example.APIProyectoBDII.Persistence.Impl;

import com.example.APIProyectoBDII.Entities.Partido;
import com.example.APIProyectoBDII.Persistence.IPartidoDAO;
import com.example.APIProyectoBDII.Repository.IPartido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
public class PartidoDAOImpl implements IPartidoDAO {

    @Autowired
    private IPartido partidoRepository;

    @Override
    public List<Partido> findAllPartidos() {
        return partidoRepository.findAllPartidos();
    }

    @Override
    public Optional<Partido> getPartido(Integer id) {
        return partidoRepository.getPartidoById(id);
    }

    @Override
    public void savePartido(LocalDate fecha, LocalTime hora, String partidoLocal, String partidoVisitante, Integer goles_pais_local, Integer goles_pais_visitante) {
        partidoRepository.savePartido( fecha, hora, partidoLocal, partidoVisitante, goles_pais_local, goles_pais_visitante);
    }

    @Override
    public void deletePartido(Integer id) {
        partidoRepository.deletePartidoById(id);
    }

    @Override
    public int loadScore(Integer id, Integer goles_pais_local, Integer goles_pais_visitante) {
        return partidoRepository.loadScore(id, goles_pais_local, goles_pais_visitante);
    }
}
