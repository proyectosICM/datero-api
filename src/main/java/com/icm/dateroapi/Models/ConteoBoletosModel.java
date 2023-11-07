package com.icm.dateroapi.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ConteoBoletos")
public class ConteoBoletosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Integer conteo;
    private LocalDate dia;

    @Column(nullable = true)
    private Double totalAcumulado;

    @ManyToOne
    @JoinColumn(name = "boletos", referencedColumnName = "id", nullable = false)
    private BoletosModel boletosModel;

    @ManyToOne
    @JoinColumn(name = "buses", referencedColumnName = "id", nullable = false)
    private BusesModel busesModel;

    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "id", nullable = false)
    private EmpresasModel empresasModel;
}
