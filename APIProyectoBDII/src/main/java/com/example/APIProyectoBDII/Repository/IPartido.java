package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Controllers.DTO.PartidoPrediccionDTO;
import com.example.APIProyectoBDII.Entities.Partido;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPartido extends CrudRepository<Partido, Long> {

    @Query(value = "SELECT * FROM Partido", nativeQuery = true)
    public List<Partido> findAllPartidos();

    @Query(value = "SELECT * FROM Partido WHERE Partido.id = ?1", nativeQuery = true)
    public Optional<Partido> getPartidoById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Partido (fecha, hora, id_pais_local, id_pais_visitante, goles_pais_local, goles_pais_visitante) VALUES (:fecha, :hora, :id_pais_local, :id_pais_visitante, :goles_pais_local, :goles_pais_visitante)", nativeQuery = true)
    public void savePartido(@Param("fecha") LocalDate fecha, @Param("hora") LocalTime hora, @Param("id_pais_local") String id_pais_local, @Param("id_pais_visitante") String id_pais_visitante, @Param("goles_pais_local") Integer goles_pais_local, @Param("goles_pais_visitante") Integer goles_pais_visitante);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Partido WHERE Partido.id = ?1", nativeQuery = true)
    public void deletePartidoById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Partido SET goles_pais_local = :goles_pais_local, goles_pais_visitante = :goles_pais_visitante WHERE Partido.id = :id", nativeQuery = true)
    public int loadScore(@Param("id") Integer id, @Param("goles_pais_local") Integer goles_pais_local, @Param("goles_pais_visitante") Integer goles_pais_visitante);

    @Query(value = "SELECT Partido.id as id, Partido.fecha as fecha, Partido.hora as hora, Partido.id_pais_local as id_pais_local, Partido.id_pais_visitante as id_pais_visitante, Partido.goles_pais_local as goles_pais_local, Partido.goles_pais_visitante as goles_pais_visitante, Prediccion.goles_pais_local as prediccion_pais_local, Prediccion.goles_pais_visitante as prediccion_pais_visitante FROM Partido LEFT JOIN Prediccion ON Partido.id = Prediccion.id_partido AND Prediccion.id_participante = ?1", nativeQuery = true)
    public List<PartidoPrediccionDTO> findAllPartidosById(Integer id);
}
