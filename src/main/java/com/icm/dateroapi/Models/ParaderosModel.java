package com.icm.dateroapi.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Paraderos")
public class ParaderosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String nombre;
    private Boolean estado;

    @Column(precision = 20, scale = 15)
    private BigDecimal longitud;

    @Column(precision = 20, scale = 15)
    private BigDecimal  latitud;

    @ManyToOne
    @JoinColumn(name = "distrito", referencedColumnName = "id", nullable = false)
    private DistritosModel distritosModel;
}
