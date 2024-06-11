package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Entities.Carrera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarrera extends CrudRepository<Carrera, String> {


    @Query(value = "SELECT * FROM Carrera", nativeQuery = true)
    public List<Carrera> getAllCarreraes();
}
