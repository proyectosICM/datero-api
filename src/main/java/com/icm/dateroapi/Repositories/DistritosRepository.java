package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.DistritosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistritosRepository extends JpaRepository<DistritosModel, Long> {
    List<DistritosModel> findByEstado(Boolean estado);
}
