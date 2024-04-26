package com.icm.dateroapi.repositories;

import com.icm.dateroapi.models.RegistroRutaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRutaRepository extends JpaRepository<RegistroRutaModel, Long> {
}