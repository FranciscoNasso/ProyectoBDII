package com.example.APIProyectoBDII.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Score {
    @Id
    @Column(name = "Puntos")
    private Integer points;

    @Column(name = "Justificacion")
    private String reason;
}
