package com.example.APIProyectoBDII.Repository;


import com.example.APIProyectoBDII.Entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuario extends CrudRepository<Usuario, Long> {


    @Query(value = "SELECT * FROM Usuario", nativeQuery = true)
    public List<Usuario> findAllusers();

    @Query(value = "SELECT * FROM Usuario WHERE Usuario.id = ?1", nativeQuery = true)
    public Optional<Usuario> getuserById(int id);

    @Query(value = "DELETE FROM Usuario WHERE Usuario.id = ?1", nativeQuery = true)
    public void deleteuserById(int id);

    @Query(value = "INSERT INTO Usuario ?1", nativeQuery = true)
    public void createuser(Usuario usuario);
}
