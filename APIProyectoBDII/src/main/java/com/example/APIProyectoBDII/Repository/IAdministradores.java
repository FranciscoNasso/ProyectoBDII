package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Entities.Administradores;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;

public interface IAdministradores {


    @Query(value = "SELECT * FROM Administradores WHERE Administradores.id = ?1", nativeQuery = true)
    public User getUserById(int id);

    @Query(value = "DELETE FROM Administradores WHERE Administradores.id = ?1", nativeQuery = true)
    public void deleteUserById(int id);

    @Query(value = "INSERT INTO Administradores ?1", nativeQuery = true)
    public void crearAdministradores(Administradores administrador);


}
