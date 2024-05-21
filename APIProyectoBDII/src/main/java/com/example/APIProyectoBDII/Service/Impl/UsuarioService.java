package com.example.APIProyectoBDII.Service.Impl;

import com.example.APIProyectoBDII.Entities.Usuario;
import com.example.APIProyectoBDII.Persistence.IUsuarioDAO;
import com.example.APIProyectoBDII.Service.IUsuarioService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    @Override
    public Usuario findById(Integer id) {
        return usuarioDAO.findById(id);
    }

    @Override
    public void save(Usuario usuario) {
        usuarioDAO.save(usuario);

    }

    @Override
    public void delete(Integer id) {
        usuarioDAO.delete(id);
    }
}
