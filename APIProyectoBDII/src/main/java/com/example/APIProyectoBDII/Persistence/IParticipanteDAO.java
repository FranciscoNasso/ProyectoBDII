package com.example.APIProyectoBDII.Persistence;

import com.example.APIProyectoBDII.Entities.Participante;

import java.util.List;
import java.util.Optional;

public interface IParticipanteDAO {

    List<Participante> findAllPart();

    Optional<Participante> findById(Integer id);

    public int save(int id, String campeon, String subcampeon);

    void delete(int id);
}
