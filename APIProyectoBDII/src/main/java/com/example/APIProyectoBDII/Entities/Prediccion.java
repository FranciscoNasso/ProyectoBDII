package com.example.APIProyectoBDII.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Prediccion {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partido")
    private Partido idPartido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_participante")
    private Participante idParticipante;

    @Column(name = "goles_paisLocal")
    private Integer golesPaislocal;

    @Column(name = "goles_paisVisitante")
    private Integer golesPaisvisitante;

}