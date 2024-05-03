package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUser {

    @Query(value = "SELECT * FROM User WHERE user.id = ?1", nativeQuery = true)
    public String getByCedula(Integer cedula);

    @Query(value = "INSERT INTO User ?1", nativeQuery = true)
    public void saveUser(User user);

    @Query(value = "DELETE FROM User WHERE User.id = ?1", nativeQuery = true)
    public void deleteUserById(Integer cedula);
}
