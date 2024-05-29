package com.example.APIProyectoBDII.Service;

import com.example.APIProyectoBDII.Entities.Pais;

import java.util.List;
import java.util.Optional;

public interface IPaisService {
    public List<Pais> findAllPais();

    public Optional<Pais> findById(String nombre);

    public void save(String nombre);

    public void delete(String nombre);
}
