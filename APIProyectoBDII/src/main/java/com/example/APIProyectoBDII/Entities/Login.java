package com.example.APIProyectoBDII.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Login {
    
    @Id
    @Column(name = "ci", nullable = false)
    private Integer id;

    @Column(name = "contrasenia", length = 50)
    private String nombre;

}