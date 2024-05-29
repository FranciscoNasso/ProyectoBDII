package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Entities.Participante;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IParticipante extends CrudRepository<Participante, Integer> {

    @Query(value = "SELECT * FROM Participante WHERE Participante.id = ?1", nativeQuery = true)
    public Optional<Participante> getUserById(int id);

    @Query(value = "SELECT * FROM Participante", nativeQuery = true)
    public List<Participante> findAllPart();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Participante WHERE Participante.id = ?1", nativeQuery = true)
    public void deleteUserById(int id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Participante (id) VALUES (:id)", nativeQuery = true)
    public void crearPart(@Param("id") int id);
}
