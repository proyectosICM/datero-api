package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.BusesModel;
import com.icm.dateroapi.Models.EmpresasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusesRepository extends JpaRepository<BusesModel, Long> {
    List<BusesModel> findByEmpresasModelId(Long empresaid);
    List<BusesModel> findByEmpresasModelIdAndEstado(Long empresaid, Boolean estado );
}
