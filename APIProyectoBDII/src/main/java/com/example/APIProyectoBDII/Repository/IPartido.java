package com.example.APIProyectoBDII.Repository;

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
    public List<Partido> getPartidos();

    @Query(value = "SELECT * FROM Partido WHERE Partido.id = ?1", nativeQuery = true)
    public Optional<Partido> getPartidoById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Partido (id, fecha, hora, paisLocal, paisVisitante) VALUES (:id, :fecha, :hora, :paisLocal, :paisVisitante)", nativeQuery = true)
    public void savePartido(@Param("id")  Integer id, @Param("fecha") LocalDate fecha, @Param("hora") LocalTime hora, @Param("paisLocal") String paisLocal, @Param("paisVisitante") String paisVisitante);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Partido WHERE Partido.id = ?1")
    public void deletePartidoById(Integer id);
}
