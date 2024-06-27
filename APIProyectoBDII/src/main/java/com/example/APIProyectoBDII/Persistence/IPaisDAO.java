package com.example.APIProyectoBDII.Persistence;

import com.example.APIProyectoBDII.Entities.Pais;

import java.util.List;
import java.util.Optional;

public interface IPaisDAO {

    List<Pais> findAllPais();

    Optional<Pais> findById(String nombre);

    public void save(String nombre, String iso);

    void delete(String nombre);
}
