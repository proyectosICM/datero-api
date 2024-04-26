package com.icm.dateroapi.repositories;

import com.icm.dateroapi.models.RPModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RPRepository extends JpaRepository<RPModel, Long> {
    List<RPModel> findByRutasModelId(Long rutaid);
}