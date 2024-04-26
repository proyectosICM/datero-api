package com.icm.dateroapi.repositories;

import com.icm.dateroapi.models.UsuariosModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long> {
    Page<UsuariosModel> findByEmpresasModelId (Long empresaid, Pageable pageable);
    Page<UsuariosModel> findByEmpresasModelIdAndEstado(Long empresaid, Boolean estado, Pageable pageable);
}