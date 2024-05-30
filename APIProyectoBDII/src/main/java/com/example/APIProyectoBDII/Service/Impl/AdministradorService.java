package com.example.APIProyectoBDII.Service.Impl;

import com.example.APIProyectoBDII.Entities.Administrador;
import com.example.APIProyectoBDII.Persistence.IAdministradorDAO;
import com.example.APIProyectoBDII.Service.IAdministradorService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService implements IAdministradorService {

    @Autowired
    private IAdministradorDAO administradorDAO;

    @Override
    public int checkExistence(Integer id) {
        return administradorDAO.checkExistence(id);
    }
}
