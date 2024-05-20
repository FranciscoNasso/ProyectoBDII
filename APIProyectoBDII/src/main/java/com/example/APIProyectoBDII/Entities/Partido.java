package com.example.APIProyectoBDII.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Partido {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paisLocal")
    private Pais idPaislocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paisVisitante")
    private Pais idPaisvisitante;

    @Column(name = "goles_paisLocal")
    private Integer golesPaislocal;

    @Column(name = "goles_paisVisitante")
    private Integer golesPaisvisitante;

}