package com.example.APIProyectoBDII.Service.Impl;

import com.example.APIProyectoBDII.Entities.Usuario;
import com.example.APIProyectoBDII.Persistence.IUsuarioDAO;
import com.example.APIProyectoBDII.Service.IUsuarioService;
import org.apache.catalina.User;

import java.util.List;

public class UsuarioService implements IUsuarioService {

    private IUsuarioDAO usuarioDAO;
    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    @Override
    public User findById(Integer id) {
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
