package com.example.APIProyectoBDII.Persistence;

import com.example.APIProyectoBDII.Entities.Carrera;

import java.util.List;

public interface ICarreraDAO {

    List<Carrera> findAllCarrera();
}
