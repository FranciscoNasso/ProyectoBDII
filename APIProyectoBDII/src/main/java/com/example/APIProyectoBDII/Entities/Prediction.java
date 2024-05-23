package com.example.APIProyectoBDII.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prediction {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "prediccion_local")
    private int predic_local;
    @Column(name = "prediccion_visitante")
    private int predic_visit;
}