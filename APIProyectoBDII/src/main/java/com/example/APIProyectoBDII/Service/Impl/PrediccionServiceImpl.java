package com.example.APIProyectoBDII.Service.Impl;

import com.example.APIProyectoBDII.Entities.Prediccion;
import com.example.APIProyectoBDII.Persistence.IPrediccionDAO;
import com.example.APIProyectoBDII.Service.IPrediccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PrediccionServiceImpl implements IPrediccionService {

    @Autowired
    private IPrediccionDAO prediccionDAO;

    @Override
    public int puntuar(Integer id_partido, Integer goles_pais_visitante, Integer goles_pais_local) {
        return prediccionDAO.puntuar(id_partido, goles_pais_visitante, goles_pais_local);
    }

    @Override
    public int save(Integer id_partido, Integer id_participante, Integer goles_pais_local, Integer goles_pais_visitante) {
        return prediccionDAO.save(id_partido, id_participante, goles_pais_local, goles_pais_visitante);
    }

    @Override
    public int getPuntaje(Integer id_participante) {
        return prediccionDAO.getPuntaje(id_participante);
    }

    @Override
    public Map<Integer, Integer> getRanking() {
        return prediccionDAO.getRanking();
    }
}
