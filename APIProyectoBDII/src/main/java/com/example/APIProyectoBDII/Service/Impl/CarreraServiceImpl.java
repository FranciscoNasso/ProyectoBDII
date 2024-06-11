package com.example.APIProyectoBDII.Service.Impl;

import com.example.APIProyectoBDII.Entities.Carrera;
import com.example.APIProyectoBDII.Persistence.ICarreraDAO;
import com.example.APIProyectoBDII.Service.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraServiceImpl implements ICarreraService {

    @Autowired
    private ICarreraDAO carreraDAO;

    @Override
    public List<Carrera> findAllCarrera() {
        return carreraDAO.findAllCarrera();
    }
}
