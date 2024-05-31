package com.example.APIProyectoBDII.Persistence.Impl;

import com.example.APIProyectoBDII.Entities.Administradores;
import com.example.APIProyectoBDII.Entities.Usuario;
import com.example.APIProyectoBDII.Persistence.IAdministradorDAO;
import com.example.APIProyectoBDII.Repository.IAdministradores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdministradorDAOImpl implements IAdministradorDAO {

    @Autowired
    private IAdministradores adminstradorRepository;

    @Override
    public List<Administradores> findAll() {
        return adminstradorRepository.findAllAdmin();
    }

    @Override
    public Optional<Administradores> findById(Integer id) {
        return adminstradorRepository.getAdminById(id);
    }

    @Override
    public void save(int id) {
        adminstradorRepository.crearAdministrador(id);
    }

    @Override
    public void delete(int id) {
        adminstradorRepository.deleteAdminById(id);
    }
import com.example.APIProyectoBDII.Repository.IAdministrador;
    @Override
    public int checkExistence(Integer ci) {
        return administradorRepository.checkExistence(ci);
    }
}
