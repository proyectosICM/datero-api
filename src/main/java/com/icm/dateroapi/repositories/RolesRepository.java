package com.icm.dateroapi.repositories;

import com.icm.dateroapi.models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RolesRepository extends JpaRepository<RolesModel, Long> {
    List<RolesModel> findByEstado(Boolean estado);
}