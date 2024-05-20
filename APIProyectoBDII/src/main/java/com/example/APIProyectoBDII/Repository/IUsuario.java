package com.example.APIProyectoBDII.Repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;

public interface IUsuario {


    @Query(value = "SELECT * FROM Usuario WHERE Usuario.id = ?1", nativeQuery = true)
    public User getuserById(int id);

    @Query(value = "DELETE FROM Usuario WHERE Usuario.id = ?1", nativeQuery = true)
    public void deleteuserById(int id);

    @Query(value = "INSERT INTO Usuario ?1", nativeQuery = true)
    public void createuser(User user);
}
