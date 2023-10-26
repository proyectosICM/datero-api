package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.EmpresasModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpresasRepository extends JpaRepository<EmpresasModel, Long> {
    List<EmpresasModel>findByEstado(Boolean estado);
}
