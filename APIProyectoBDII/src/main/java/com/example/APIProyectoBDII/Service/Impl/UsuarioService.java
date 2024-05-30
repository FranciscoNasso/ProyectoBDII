package com.example.APIProyectoBDII.Service.Impl;

import com.example.APIProyectoBDII.Entities.Usuario;
import com.example.APIProyectoBDII.Persistence.IUsuarioDAO;
import com.example.APIProyectoBDII.Service.IUsuarioService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioDAO.findById(id);
    }

    @Override
    public void save(int id, String nombre, String apellido, String email) {
        usuarioDAO.save(id, nombre, apellido, email);

    }

    @Override
    public void delete(Integer id) {
        usuarioDAO.delete(id);
    }

    @Override
    public void setJWT(Integer id, String jwt) {
        usuarioDAO.setJWT(id, jwt);
    }
}
