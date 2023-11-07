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
@Table(name = "Buses")
public class BusesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String modelo;
    private String placa;

    @Column(precision = 20, scale = 15)
    private BigDecimal longitud;

    @Column(precision = 20, scale = 15)
    private BigDecimal  latitud;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = true)
    private UsuariosModel usuariosModel;

    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "id", nullable = false)
    private EmpresasModel empresasModel;

    @ManyToOne
    @JoinColumn(name = "ruta", referencedColumnName = "id", nullable = true)
    private RutasModel rutasModel;
}
