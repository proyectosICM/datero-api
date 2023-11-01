package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.BoletosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletosRepository extends JpaRepository<BoletosModel, Long> {
}
