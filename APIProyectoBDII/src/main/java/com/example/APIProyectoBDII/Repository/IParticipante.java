package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Entities.Participante;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;

public interface IParticipante {

    @Query(value = "SELECT * FROM Participante WHERE Participante.id = ?1", nativeQuery = true)
    public User getUserById(int id);

    @Query(value = "DELETE FROM Participante WHERE Participante.id = ?1", nativeQuery = true)
    public void deleteUserById(int id);

    @Query(value = "INSERT INTO Participante ?1", nativeQuery = true)
    public void crearAdministradores(Participante administrador);
}
