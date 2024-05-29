package com.example.APIProyectoBDII.Service.Impl;

import com.example.APIProyectoBDII.Entities.Participante;
import com.example.APIProyectoBDII.Persistence.IParticipanteDAO;
import com.example.APIProyectoBDII.Service.IParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteServiceImpl implements IParticipanteService {

    @Autowired
    private IParticipanteDAO participanteDAO;
    @Override
    public List<Participante> findAllPart() {
        return participanteDAO.findAllPart();
    }

    @Override
    public Optional<Participante> findById(Integer id) {
        return participanteDAO.findById(id);
    }

    @Override
    public void save(int id) {
        participanteDAO.save(id);
    }

    @Override
    public void delete(int id) {
        participanteDAO.delete(id);
    }
}
