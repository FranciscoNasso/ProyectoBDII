package com.example.APIProyectoBDII.Persistence.Impl;

import com.example.APIProyectoBDII.Entities.Partido;
import com.example.APIProyectoBDII.Persistence.IPartidoDAO;
import com.example.APIProyectoBDII.Repository.IPartido;
import lombok.RequiredArgsConstructor;
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
    public List<Partido> getPartidos() {
        return (List<Partido>) partidoRepository.getPartidos();
    }

    @Override
    public Optional<Partido> getPartido(Integer id) {
        return partidoRepository.getPartidoById(id);
    }

    @Override
    public void savePartido(Integer id, LocalDate fecha, LocalTime hora, String partidoLocal, String partidoVisitante) {
        partidoRepository.savePartido(id, fecha, hora, partidoLocal, partidoVisitante);
    }

    @Override
    public void deletePartido(Integer id) {
        partidoRepository.deletePartidoById(id);
    }
}
