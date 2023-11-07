package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.ConteoBoletosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ConteoBoletosRepository extends JpaRepository<ConteoBoletosModel, Long> {
}