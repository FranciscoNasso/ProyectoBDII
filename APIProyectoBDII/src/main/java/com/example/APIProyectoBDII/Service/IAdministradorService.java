package com.example.APIProyectoBDII.Service;

import com.example.APIProyectoBDII.Entities.Administradores;
import com.example.APIProyectoBDII.Entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IAdministradorService {

    List<Administradores> findAll();

    Optional<Administradores> findById(Integer id);

    public void save(int id);

    public void delete(int id);
import com.example.APIProyectoBDII.Entities.Administrador;
    int checkExistence(Integer id);
}
