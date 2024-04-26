package com.icm.dateroapi.repositories;

import com.icm.dateroapi.models.ParaderosModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParaderosRepository extends JpaRepository<ParaderosModel, Long> {
    List<ParaderosModel> findByEstado(Boolean estado);
    /*
    List<ParaderosModel> findByEmpresasModelIdAndEstado(Long id,Boolean estado);
     */
    //Page<ParaderosModel> findByEstado(Boolean estado, Pageable pageable);
    Page<ParaderosModel> findByEstado(Boolean estado, Pageable pageable);
}
