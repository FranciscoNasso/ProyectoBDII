package com.example.APIProyectoBDII.Persistence.Impl;

import com.example.APIProyectoBDII.Entities.Pais;
import com.example.APIProyectoBDII.Persistence.IPaisDAO;
import com.example.APIProyectoBDII.Repository.IPais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PaisDAOImpl implements IPaisDAO {

    @Autowired
    private IPais paisRepository;
    @Override
    public List<Pais> findAllPais() {
        return paisRepository.getAllPaises();
    }

    @Override
    public Optional<Pais> findById(String nombre) {
        return paisRepository.getPaisById(nombre);
    }

    @Override
    public void save(String nombre) {
        paisRepository.createPais(nombre);

    }

    @Override
    public void delete(String nombre) {
        paisRepository.deletePais(nombre);
    }
}
