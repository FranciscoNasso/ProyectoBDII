package com.example.APIProyectoBDII.Service.Impl;

import com.example.APIProyectoBDII.Controllers.DTO.PartidoPrediccionDTO;
import com.example.APIProyectoBDII.Entities.Partido;
import com.example.APIProyectoBDII.Persistence.IPartidoDAO;
import com.example.APIProyectoBDII.Service.IPartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class PartidoService implements IPartidoService {

    @Autowired
    private IPartidoDAO partidoDAO;

    @Override
    public List<Partido> findAllPartidos() {
        return partidoDAO.findAllPartidos();
    }

    @Override
    public Optional<Partido> getPartido(Integer id) {
        return partidoDAO.getPartido(id);
    }

    @Override
    public void savePartido( LocalDate fecha, LocalTime hora, String id_pais_local, String id_pais_visitante, Integer goles_pais_local, Integer goles_pais_visitante) {
        partidoDAO.savePartido(fecha, hora, id_pais_local, id_pais_visitante, goles_pais_local, goles_pais_visitante);
    }

    @Override
    public void deletePartido(Integer id) {
        partidoDAO.deletePartido(id);
    }

    @Override
    public int loadScore(Integer id, Integer goles_pais_local, Integer goles_pais_visitante) {
        return partidoDAO.loadScore(id, goles_pais_local, goles_pais_visitante);
    }

    @Override
    public List<PartidoPrediccionDTO> findAllPartidosById(Integer id) {
        return partidoDAO.findAllPartidosById(id);
    }
}
