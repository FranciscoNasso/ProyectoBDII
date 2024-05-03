package com.example.APIProyectoBDII.Repository;


import com.example.APIProyectoBDII.Entities.Rewards;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRewards {


    @Query(value = "INSERT INTO Rewards ?1", nativeQuery = true)
    public void addReward(Rewards reward);

    @Query(value = "DELETE FROM Rewards WHERE Rewards.position = ?1", nativeQuery = true)
    public void deleteReward(String posicion);
}
