package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.TiempoRutaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TiempoRutaRepository extends JpaRepository<TiempoRutaModel, Long> {
}