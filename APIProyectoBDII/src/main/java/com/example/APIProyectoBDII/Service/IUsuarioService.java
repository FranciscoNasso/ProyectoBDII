package com.example.APIProyectoBDII.Service;

import com.example.APIProyectoBDII.Entities.Usuario;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> findAll();

    Optional<Usuario> findById(Integer id);

    public void save (Usuario usuario);

    public void delete (Integer id);
}