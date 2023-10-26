package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<RolesModel, Long> {
    List<RolesModel> findByEstado(Boolean estado);
}
