package com.example.APIProyectoBDII.Service;

import com.example.APIProyectoBDII.Entities.Participante;

import java.util.List;
import java.util.Optional;

public interface IParticipanteService {

    public List<Participante> findAllPart();

    public Optional<Participante> findById(Integer id);

    public void save(int id);

    public void delete(int id);
}
