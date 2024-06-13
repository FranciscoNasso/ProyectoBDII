package com.example.APIProyectoBDII.Service;

import com.example.APIProyectoBDII.Entities.Participante;

import java.util.List;
import java.util.Optional;

public interface IParticipanteService {

    public List<Participante> findAllPart();

    public Optional<Participante> findById(Integer id);

    public int save(int id, String campeon, String subcampeon);

    public void delete(int id);
}
