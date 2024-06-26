package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Entities.Administrador;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface IAdministrador extends CrudRepository<Administrador, Long>{

    @Query(value = "SELECT COUNT(*) existe FROM Administrador WHERE Administrador.id = ?1", nativeQuery = true)
    public int checkExistence(Integer id);

    @Query(value = "SELECT * FROM Administrador", nativeQuery = true)
    public List<Administrador> findAllAdmin();


    @Query(value = "SELECT * FROM Administrador WHERE Administrador.id = ?1", nativeQuery = true)
    public Optional<Administrador> getAdminById(int id);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Administrador WHERE Administrador.id = ?1", nativeQuery = true)
    public void deleteAdminById(int id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Administrador (id) VALUES (:id)", nativeQuery = true)
    public void crearAdministrador(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Ranking SELECT Pa.id AS id_participante, COALESCE(SUM(P.puntos), 0) + CASE WHEN Pa.campeon = ?1 THEN 10 ELSE 0 END + CASE WHEN Pa.subcampeon = ?2 THEN 5 ELSE 0 END AS puntaje_final FROM Prediccion P JOIN Usuario U ON P.id_participante = U.id RIGHT JOIN Participante Pa ON P.id_participante = Pa.id GROUP BY Pa.id", nativeQuery = true)
    public int finalizar(String campeon, String subcampeon);

}
