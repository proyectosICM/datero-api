package com.icm.dateroapi.repositories;

import com.icm.dateroapi.models.EmpresasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EmpresasRepository extends JpaRepository<EmpresasModel, Long> {
    List<EmpresasModel>findByEstado(Boolean estado);
}