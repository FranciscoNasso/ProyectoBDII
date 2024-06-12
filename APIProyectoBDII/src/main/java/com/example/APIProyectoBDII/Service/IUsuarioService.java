package com.example.APIProyectoBDII.Service;

import com.example.APIProyectoBDII.Entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> findAll();

    Optional<Usuario> findById(Integer id);

    public int save (int id, String nombre, String apellido, String email, Integer id_carrera);

    public void delete (Integer id);

    public void setJWT(Integer id, String jwt);
}
