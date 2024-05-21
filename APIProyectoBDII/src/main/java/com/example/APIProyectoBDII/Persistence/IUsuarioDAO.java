package com.example.APIProyectoBDII.Persistence;

import com.example.APIProyectoBDII.Entities.Usuario;
import org.apache.catalina.User;

import java.util.List;

public interface IUsuarioDAO {

    List<Usuario> findAll();

    User findById(Integer id);

    public void save (Usuario usuario);

    public void delete (Integer id);
}
