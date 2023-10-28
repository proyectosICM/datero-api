package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.RPModel;
import com.icm.dateroapi.Models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RPRepository extends JpaRepository<RPModel, Long> {
    List<RPModel> findByRutasModelId(Long rutaid);
}
