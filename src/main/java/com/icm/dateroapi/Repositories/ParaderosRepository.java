package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.ParaderosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParaderosRepository extends JpaRepository<ParaderosModel, Long> {
    List<ParaderosModel> findByEstado(Boolean estado);

    /*
    List<ParaderosModel> findByEmpresasModelIdAndEstado(Long id,Boolean estado);
     */
}
