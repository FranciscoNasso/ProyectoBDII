package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Entities.Pais;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPais extends CrudRepository<Pais, String> {


    @Query(value = "SELECT * FROM Pais", nativeQuery = true)
    public List<Pais> getAllPaises();

    @Query(value = "SELECT * FROM Pais WHERE Pais.nombre = ?1", nativeQuery = true)
    public Optional<Pais> getPaisById(String pais);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Pais WHERE Pais.nombre = ?1", nativeQuery = true)
    public void deletePais(String pais);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Pais (nombre) VALUES :nombre", nativeQuery = true)
    public void createPais(@Param("nombre") String nombre);
}
