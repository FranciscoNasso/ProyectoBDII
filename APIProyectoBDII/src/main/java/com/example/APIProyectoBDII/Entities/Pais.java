package com.example.APIProyectoBDII.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pais")
public class Pais {
    @Id
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "iso", nullable = false, length = 2)
    private String iso;

}