package com.example.APIProyectoBDII.Repository;


import com.example.APIProyectoBDII.Entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuario extends CrudRepository<Usuario, Long> {


    @Query(value = "SELECT * FROM Usuario", nativeQuery = true)
    public List<Usuario> findAllusers();

    @Query(value = "SELECT * FROM Usuario WHERE Usuario.id = ?1", nativeQuery = true)
    public Optional<Usuario> getuserById(int id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Usuario WHERE Usuario.id = ?1", nativeQuery = true)
    public void deleteuserById(int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Usuario (id, nombre, apellido, email, id_carrera) VALUES (:id, :nombre, :apellido, :email, :id_carrera)", nativeQuery = true)
    public int createuser(@Param("id") int id, @Param("nombre") String nombre, @Param("apellido") String apellido, @Param("email") String email, @Param("id_carrera") Integer id_carrera);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuario SET jwt = :jwt WHERE Usuario.id = :id", nativeQuery = true)
    public void setJWT(Integer id, String jwt);
}
