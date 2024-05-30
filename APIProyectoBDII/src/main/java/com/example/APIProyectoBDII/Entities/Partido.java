package com.example.APIProyectoBDII.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "id_pais_local")
    private Pais idPaislocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais_visitante")
    private Pais idPaisvisitante;

    @Column(name = "goles_pais_local")
    private Integer goles_pais_local;

    @Column(name = "goles_pais_visitante")
    private Integer goles_pais_visitante;

}