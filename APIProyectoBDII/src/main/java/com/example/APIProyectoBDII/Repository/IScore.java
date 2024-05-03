package com.example.APIProyectoBDII.Repository;


import com.example.APIProyectoBDII.Entities.Score;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IScore {

    @Query(value = "INSERT INTO Score ?1", nativeQuery = true)
    public void agregarPuntuacion(Score score);


    @Query(value = "DELETE FROM Score WHERE Score.points = ?1", nativeQuery = true)
    public void eliminarPuntuacion(Integer puntos);
}
