package com.example.APIProyectoBDII.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Pais")
public class Pais {
    @Id
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    //TODO [Reverse Engineering] generate columns from DB
}