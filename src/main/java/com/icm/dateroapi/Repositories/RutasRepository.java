package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.EmpresasModel;
import com.icm.dateroapi.Models.RutasModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutasRepository extends JpaRepository<RutasModel, Long> {
    List<RutasModel> findByEmpresasModelIdAndEstado(Long empresasModel, Boolean estado);
    List<RutasModel> findByEmpresasModelId(Long empresasModel);
}
