package com.example.APIProyectoBDII.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Partido")
public class Match {
    @Id
    private Integer id;
    private String date;
    private String time;
    @ManyToOne
    @JoinColumn(name = "Local")
    private Country local;
    @ManyToOne
    @JoinColumn(name = "Visitante")
    private Country visitante;
}
