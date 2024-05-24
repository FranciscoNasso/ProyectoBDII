package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Entities.Administradores;
import com.example.APIProyectoBDII.Entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IAdministradores {

    @Query(value = "SELECT * FROM Administradores", nativeQuery = true)
    public List<Administradores> findAllAdmin();


    @Query(value = "SELECT * FROM Administradores WHERE Administradores.id = ?1", nativeQuery = true)
    public Optional<Administradores> getAdminById(int id);

    @Query(value = "DELETE FROM Administradores WHERE Administradores.id = ?1", nativeQuery = true)
    public void deleteAdminById(int id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Administradores (id) VALUES (:id)", nativeQuery = true)
    public void crearAdministrador(@Param("id") int id);


}
