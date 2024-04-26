package com.icm.dateroapi.repositories;

import com.icm.dateroapi.models.TiempoRutaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TiempoRutaRepository extends JpaRepository<TiempoRutaModel, Long> {
}