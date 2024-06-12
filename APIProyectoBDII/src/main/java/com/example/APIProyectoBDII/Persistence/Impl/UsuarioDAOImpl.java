package com.example.APIProyectoBDII.Persistence.Impl;

import com.example.APIProyectoBDII.Entities.Usuario;
import com.example.APIProyectoBDII.Persistence.IUsuarioDAO;
import com.example.APIProyectoBDII.Repository.IUsuario;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioDAOImpl implements IUsuarioDAO {

    private final IUsuario usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAllusers();
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
       return usuarioRepository.getuserById(id);
    }

    @Override
    public int save(int id, String nombre, String apellido, String email, Integer id_carrera) {
        return usuarioRepository.createuser(id, nombre, apellido, email, id_carrera);
    }

    @Override
    public void delete(Integer id) {
        usuarioRepository.deleteuserById(id);

    }

    @Override
    public void setJWT(Integer id, String jwt) {
        usuarioRepository.setJWT(id, jwt);
    }
}
