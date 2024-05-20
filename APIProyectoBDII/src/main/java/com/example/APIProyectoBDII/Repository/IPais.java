package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Entities.Pais;
import org.springframework.data.jpa.repository.Query;

public interface IPais {


    @Query(value = "SELECT * FROM Pais WHERE Pais.nombre = ?1", nativeQuery = true)
    public String getPaisById(String pais);

    @Query(value = "DELETE FROM Pais WHERE Pais.nombre = ?1", nativeQuery = true)
    public void deletePais(Pais pais);

    @Query(value = "INSERT INTO Pais ?1", nativeQuery = true)
    public void createPais(Pais pais);
}
