package com.icm.dateroapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RegistroRuta")
public class RegistroRutaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private LocalDate dia;

    @Temporal(TemporalType.TIME)
    private Time horaEsperada;

    @Temporal(TemporalType.TIME)
    private Time  horaLlegada;

    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "id", nullable = false)
    private EmpresasModel empresasModel;

    @ManyToOne
    @JoinColumn(name = "ruta", referencedColumnName = "id", nullable = false)
    private RutasModel rutasModel;

    @ManyToOne
    @JoinColumn(name = "bus", referencedColumnName = "id", nullable = false)
    private BusesModel busesModel;

    @ManyToOne
    @JoinColumn(name = "paradero", referencedColumnName = "id", nullable = false)
    private ParaderosModel paraderosModel;
}
