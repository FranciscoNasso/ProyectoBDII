package com.example.APIProyectoBDII.Persistence.Impl;

import com.example.APIProyectoBDII.Entities.Participante;
import com.example.APIProyectoBDII.Persistence.IParticipanteDAO;
import com.example.APIProyectoBDII.Repository.IParticipante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ParticipanteDAOImpl implements IParticipanteDAO {

    @Autowired
    private IParticipante participanteRepository;

    @Override
    public List<Participante> findAllPart() {
        return participanteRepository.findAllPart();
    }

    @Override
    public Optional<Participante> findById(Integer id) {
        return participanteRepository.getUserById(id);
    }

    @Override
    public int save(int id, String campeon, String subcampeon) {
        return participanteRepository.crearPart(id, campeon, subcampeon);
    }

    @Override
    public void delete(int id) {
        participanteRepository.deleteUserById(id);
    }
}
