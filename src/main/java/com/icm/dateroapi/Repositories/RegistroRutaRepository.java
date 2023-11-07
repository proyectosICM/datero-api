package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.RegistroRutaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRutaRepository extends JpaRepository<RegistroRutaModel, Long> {
}