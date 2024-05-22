package com.example.APIProyectoBDII.Persistence;

import com.example.APIProyectoBDII.Entities.Usuario;


import java.util.List;
import java.util.Optional;

public interface IUsuarioDAO {

    List<Usuario> findAll();

    Optional<Usuario> findById(Integer id);

    public void save ( int id, String usuario, String apellido, String email);

    public void delete (Integer id);
}
