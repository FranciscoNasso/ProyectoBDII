package com.example.APIProyectoBDII.Service.Impl;

import com.example.APIProyectoBDII.Entities.Administrador;

import com.example.APIProyectoBDII.Persistence.IAdministradorDAO;
import com.example.APIProyectoBDII.Service.IAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdministradorService implements IAdministradorService {

    @Autowired
    private IAdministradorDAO administradorDAO;

    @Override
    public List<Administrador> findAll() {
        return administradorDAO.findAll();
    }

    @Override
    public Optional<Administrador> findById(Integer id) {
        return administradorDAO.findById(id);
    }

    @Override
    public void save(int id) {
        administradorDAO.save(id);
    }

    @Override
    public void delete(int id) {
        administradorDAO.delete(id);
    }

    public int checkExistence(Integer id) {
        return administradorDAO.checkExistence(id);
    }
}
