package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Entities.Administrador;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.apache.catalina.User;


@Repository
public interface IAdministrador extends CrudRepository<Administrador, Long>{

    @Query(value = "SELECT COUNT(*) existe FROM Administradores WHERE Administradores.id = ?1", nativeQuery = true)
    public int checkExistence(Integer id);

}
