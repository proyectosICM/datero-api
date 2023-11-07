package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.BoletosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BoletosRepository extends JpaRepository<BoletosModel, Long> {
    List<BoletosModel> findByEmpresasModelIdAndRutasModelId(Long empresaid, Long rutaid);
}