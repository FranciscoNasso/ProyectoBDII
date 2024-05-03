package com.example.APIProyectoBDII.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Country {
    @Id
    @Column(name = "Nombre", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "local")
    private List<Match> local;

    @OneToMany(mappedBy = "visitante")
    private List<Match> visitante;
}
