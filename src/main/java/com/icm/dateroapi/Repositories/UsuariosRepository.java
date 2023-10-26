package com.icm.dateroapi.Repositories;

import com.icm.dateroapi.Models.EmpresasModel;
import com.icm.dateroapi.Models.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long> {
    List<UsuariosModel> findByEmpresasModelId (Long empresaid);
    List<UsuariosModel> findByEmpresasModelIdAndEstado(Long empresaid, Boolean estado);
}
