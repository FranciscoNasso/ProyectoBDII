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
@Entity
public class Administrador {

    @Id
    @Column(name = "admin")
    private Integer id;

    @JoinColumn(name = "admin")
    @ManyToOne
    private User User;
cod

}

