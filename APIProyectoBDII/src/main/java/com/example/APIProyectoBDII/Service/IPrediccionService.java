package com.example.APIProyectoBDII.Service;

import com.example.APIProyectoBDII.Entities.Prediccion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IPrediccionService {

    public int puntuar(Integer id_partido, Integer goles_pais_visitante, Integer goles_pais_local);

    public int save(Integer id_partido, Integer id_participante, Integer goles_pais_local, Integer goles_pais_visitante);

    public int getPuntaje(Integer id_participante);

    public Map<Integer, Integer> getRanking();
}
