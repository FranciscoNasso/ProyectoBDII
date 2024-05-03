package com.example.APIProyectoBDII.Repository;

import com.example.APIProyectoBDII.Entities.Country;
import org.springframework.data.jpa.repository.Query;

public interface ICountry {

    @Query(value = "Select * FROM Country WHERE country.name = ?1", nativeQuery = true)
    public String getCountry(String country);

    @Query(value = "insert into Country ?1", nativeQuery = true)
    public void saveCountry(Country country);

    @Query(value = "DELETE from Country where country.name = ?1", nativeQuery = true)
    public void deleteCountry(String country);

}
