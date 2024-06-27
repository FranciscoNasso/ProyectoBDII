package com.example.APIProyectoBDII.Service.Impl;

import com.example.APIProyectoBDII.Entities.Pais;
import com.example.APIProyectoBDII.Persistence.IPaisDAO;
import com.example.APIProyectoBDII.Service.IPaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisServiceImpl implements IPaisService {

    @Autowired
    private IPaisDAO paisDAO;

    @Override
    public List<Pais> findAllPais() {
        return paisDAO.findAllPais();
    }

    @Override
    public Optional<Pais> findById(String nombre) {
        return paisDAO.findById(nombre);
    }

    @Override
    public void save(String nombre, String iso) {
        paisDAO.save(nombre, iso);
    }

    @Override
    public void delete(String nombre) {
        paisDAO.delete(nombre);
    }
}
