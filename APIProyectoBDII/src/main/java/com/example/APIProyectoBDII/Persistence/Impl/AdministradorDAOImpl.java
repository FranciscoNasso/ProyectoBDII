package com.example.APIProyectoBDII.Persistence.Impl;

import com.example.APIProyectoBDII.Entities.Administrador;
import com.example.APIProyectoBDII.Entities.Usuario;
import com.example.APIProyectoBDII.Persistence.IAdministradorDAO;
import com.example.APIProyectoBDII.Repository.IAdministrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdministradorDAOImpl implements IAdministradorDAO {

    @Autowired
    private IAdministrador administradorRepository;

    @Override
    public List<Administrador> findAll() {
        return administradorRepository.findAllAdmin();
    }

    @Override
    public Optional<Administrador> findById(Integer id) {
        return administradorRepository.getAdminById(id);
    }

    @Override
    public void save(int id) {
        administradorRepository.crearAdministrador(id);
    }

    @Override
    public void delete(int id) {
        administradorRepository.deleteAdminById(id);
    }

    @Override
    public int checkExistence(Integer ci) {
        return administradorRepository.checkExistence(ci);
    }
}
