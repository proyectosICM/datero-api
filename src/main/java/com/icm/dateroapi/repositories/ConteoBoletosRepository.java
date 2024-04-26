package com.icm.dateroapi.repositories;

import com.icm.dateroapi.models.ConteoBoletosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ConteoBoletosRepository extends JpaRepository<ConteoBoletosModel, Long> {
}