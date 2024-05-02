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
public class User {
    @Id
    @Column(name = "Cedula", unique = true, nullable = false)
    private Integer id;
    @Column (name = "Nombre")
    private String name;
    @Column(name = "Apellido")
    private String apellido;
    @Column(name = "Email")
    private String email;


}
