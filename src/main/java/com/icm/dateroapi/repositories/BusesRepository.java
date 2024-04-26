package com.icm.dateroapi.repositories;

import com.icm.dateroapi.models.BusesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BusesRepository extends JpaRepository<BusesModel, Long> {
    List<BusesModel> findByEmpresasModelId(Long empresaid);
    List<BusesModel> findByEmpresasModelIdAndEstado(Long empresaid, Boolean estado );
    Page<BusesModel> findByEmpresasModelId(Long empresaid, Pageable pageable);
    Page<BusesModel> findByEmpresasModelIdAndEstado(Long empresaid, Boolean estado, Pageable pageable );
}