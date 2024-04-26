package com.icm.dateroapi.repositories;

import com.icm.dateroapi.models.RutasModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RutasRepository extends JpaRepository<RutasModel, Long> {
    Page<RutasModel> findByEmpresasModelId(Long empresaId, Pageable pageable);
    Page<RutasModel> findByEmpresasModelIdAndEstado(Long empresaId, Boolean estado, Pageable pageable);
}