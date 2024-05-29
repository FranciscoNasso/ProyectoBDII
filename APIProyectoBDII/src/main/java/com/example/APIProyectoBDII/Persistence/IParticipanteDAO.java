package com.example.APIProyectoBDII.Persistence;

import com.example.APIProyectoBDII.Entities.Participante;

import java.util.List;
import java.util.Optional;

public interface IParticipanteDAO {

    List<Participante> findAllPart();

    Optional<Participante> findById(Integer id);

    public void save(int id);

    void delete(int id);
}
