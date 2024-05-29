package com.example.APIProyectoBDII.Entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participante {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Usuario usuario;

}