package com.example.APIProyectoBDII.Persistence;

import com.example.APIProyectoBDII.Controllers.DTO.RankingDTO;
import com.example.APIProyectoBDII.Entities.Pais;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IPrediccionDAO {

    public int puntuar(Integer id_partido, Integer goles_pais_visitante, Integer goles_pais_local);

    public int save(Integer id_partido, Integer id_participante, Integer goles_pais_local, Integer goles_pais_visitante);

    public int getPuntaje(Integer id_participante);

    public List<RankingDTO> getRanking();
}
