package com.example.APIProyectoBDII.Persistence;

import com.example.APIProyectoBDII.Entities.Usuario;


import java.util.List;
import java.util.Optional;

public interface IUsuarioDAO {

    List<Usuario> findAll();

    Optional<Usuario> findById(Integer id);

    public int save ( int id, String usuario, String apellido, String email, Integer id_carrera);

    public void delete (Integer id);

    void setJWT(Integer id, String jwt);
}
