package com.example.APIProyectoBDII.Persistence;

import com.example.APIProyectoBDII.Entities.Login;


import java.util.List;
import java.util.Optional;

public interface ILoginDAO {

    Optional<Login> findById(Integer ci);

    List<Login> findAll();

    String getContrasenia(Integer ci);
}
