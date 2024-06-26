package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Entities.Prediccion;
import com.example.APIProyectoBDII.Controllers.DTO.RankingDTO;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrediccion extends CrudRepository<Prediccion, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Prediccion SET puntos = CASE WHEN (goles_pais_local = :goles_pais_local AND goles_pais_visitante = :goles_pais_visitante) THEN 4 WHEN ((goles_pais_local > goles_pais_visitante AND :goles_pais_local > :goles_pais_visitante) OR (goles_pais_local < goles_pais_visitante AND :goles_pais_local < :goles_pais_visitante) OR (goles_pais_local = goles_pais_visitante AND :goles_pais_local = :goles_pais_visitante)) THEN 2 ELSE 0 END WHERE id_partido = :id_partido", nativeQuery = true)
    public int puntuar(@Param("id_partido") Integer id_partido, @Param("goles_pais_visitante") Integer goles_pais_visitante, @Param("goles_pais_local") Integer goles_pais_local);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE Prediccion SET puntos = 2 WHERE id_partido = :id_partido", nativeQuery = true)
    public int puntuar2(@Param("id_partido") Integer id_partido);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Prediccion (id_partido, id_participante, goles_pais_local, goles_pais_visitante) VALUES (:id_partido, :id_participante, :goles_pais_local, :goles_pais_visitante)", nativeQuery = true)
    public int save(@Param("id_partido") int id_partido, @Param("id_participante") int id_participante, @Param("goles_pais_local") int goles_pais_local, @Param("goles_pais_visitante") int goles_pais_visitante);

    @Query(value = "SELECT Prediccion.id_participante AS id_participante, Usuario.nombre AS nombre, Usuario.apellido AS apellido, SUM(puntos) AS puntos FROM Prediccion INNER JOIN Usuario ON Prediccion.id_participante = Usuario.id GROUP BY id_participante", nativeQuery = true)
    public List<RankingDTO> getRanking();

    @Query(value = "SELECT SUM(puntos) AS puntos FROM Prediccion WHERE id_participante = :id_participante", nativeQuery = true)
    public int getPuntaje(@Param("id_participante") int id_participante);
}
