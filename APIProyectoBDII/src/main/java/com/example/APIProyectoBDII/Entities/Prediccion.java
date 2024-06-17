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
    private Partido id_partido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_participante")
    private Participante id_participante;

    @Column(name = "goles_pais_local")
    private Integer goles_pais_local;

    @Column(name = "goles_pais_visitante")
    private Integer goles_pais_visitante;

   @Column(name = "puntos")
   private Integer puntos; 

}