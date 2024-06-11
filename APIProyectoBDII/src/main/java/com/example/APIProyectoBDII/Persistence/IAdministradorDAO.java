package com.example.APIProyectoBDII.Persistence;

import com.example.APIProyectoBDII.Entities.Administrador;

import java.util.List;
import java.util.Optional;

public interface IAdministradorDAO {

    List<Administrador> findAll();

    Optional<Administrador> findById(Integer id);

    public void save(int id);

    void delete(int id);
    
    int checkExistence(Integer id);

}