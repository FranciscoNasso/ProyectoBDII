package com.example.APIProyectoBDII.Repository;


import com.example.APIProyectoBDII.Entities.Login;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILogin extends CrudRepository<Login, Long> {

    @Query(value = "SELECT * FROM Login", nativeQuery = true)
    public List<Login> findAlllogin();

    @Query(value = "SELECT * FROM Login WHERE Login.ci = ?1", nativeQuery = true)
    public Optional<Login> getloginById(int ci);

    @Query(value = "SELECT contrasenia FROM Login WHERE Login.ci = ?1", nativeQuery = true)
    public String getContrasenia(Integer ci);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Login (ci, contrasenia) VALUES (:ci, :contrasenia)", nativeQuery = true)
    public int save(Integer ci, String contrasenia);

}
