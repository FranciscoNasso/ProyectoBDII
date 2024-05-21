package com.example.APIProyectoBDII.Service;

import com.example.APIProyectoBDII.Entities.Usuario;
import org.apache.catalina.User;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> findAll();

    Usuario findById(Integer id);

    public void save (Usuario usuario);

    public void delete (Integer id);
}
