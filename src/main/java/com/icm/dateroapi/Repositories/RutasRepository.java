package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.RutasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RutasRepository extends JpaRepository<RutasModel, Long> {
    List<RutasModel> findByEmpresasModelIdAndEstado(Long empresasModel, Boolean estado);
    List<RutasModel> findByEmpresasModelId(Long empresasModel);
}