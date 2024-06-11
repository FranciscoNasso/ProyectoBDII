package com.example.APIProyectoBDII.Persistence.Impl;

import com.example.APIProyectoBDII.Entities.Carrera;
import com.example.APIProyectoBDII.Persistence.ICarreraDAO;
import com.example.APIProyectoBDII.Repository.ICarrera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CarreraDAOImpl implements ICarreraDAO {

    @Autowired
    private ICarrera carreraRepository;
    @Override
    public List<Carrera> findAllCarrera() {
        return carreraRepository.getAllCarreraes();
    }
}
