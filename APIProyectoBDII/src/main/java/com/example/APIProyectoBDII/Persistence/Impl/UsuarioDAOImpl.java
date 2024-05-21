package com.example.APIProyectoBDII.Persistence.Impl;

import com.example.APIProyectoBDII.Entities.Usuario;
import com.example.APIProyectoBDII.Persistence.IUsuarioDAO;
import com.example.APIProyectoBDII.Repository.IUsuario;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioDAOImpl implements IUsuarioDAO {

    @Autowired
    private IUsuario usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAllusers();
    }

    @Override
    public User findById(Integer id) {
       return usuarioRepository.getuserById(id);
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.createuser(usuario);
    }

    @Override
    public void delete(Integer id) {
        usuarioRepository.deleteuserById(id);

    }
}
