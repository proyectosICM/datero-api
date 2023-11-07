package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.EmpresasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EmpresasRepository extends JpaRepository<EmpresasModel, Long> {
    List<EmpresasModel>findByEstado(Boolean estado);
}