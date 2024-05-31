package com.example.APIProyectoBDII.Service;

import com.example.APIProyectoBDII.Entities.Login;

import java.util.List;
import java.util.Optional;

public interface ILoginService {

    List<Login> findAll();

    Optional<Login> findById(Integer ci);

    String getContrasenia(Integer ci);
}
