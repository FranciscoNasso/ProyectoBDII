package com.example.APIProyectoBDII.Persistence.Impl;

import com.example.APIProyectoBDII.Entities.Prediccion;
import com.example.APIProyectoBDII.Persistence.IPrediccionDAO;
import com.example.APIProyectoBDII.Repository.IPrediccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class PrediccionDAOImpl implements IPrediccionDAO {

    @Autowired
    private IPrediccion prediccionRepository;

    @Override
    public int puntuar(Integer id_partido, Integer goles_pais_visitante, Integer goles_pais_local) {
        return prediccionRepository.puntuar(id_partido, goles_pais_visitante, goles_pais_local);
        // return prediccionRepository.puntuar2(id_partido);
    }

    @Override
    public int save(Integer id_partido, Integer id_participante, Integer goles_pais_local, Integer goles_pais_visitante) {
        return prediccionRepository.save(id_partido, id_participante, goles_pais_local, goles_pais_visitante);
    }

    @Override
    public int getPuntaje(Integer id_participante){
        return prediccionRepository.getPuntaje(id_participante);
    }

    @Override
    public Map<Integer, Integer> getRanking(){
        return prediccionRepository.getRanking();
    }
}
