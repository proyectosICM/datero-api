package com.icm.dateroapi.services;

import com.icm.dateroapi.models.UsuariosModel;
import com.icm.dateroapi.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {
    @Autowired
    private UsuariosRepository usuariosRepository;

    public List<UsuariosModel> getAll(){
        return usuariosRepository.findAll();
    }

    public Optional<UsuariosModel> getById(Long id){
        return usuariosRepository.findById(id);
    }
    public Page<UsuariosModel> getByEmpresa(Long empresaid, Pageable pageable) {
        return usuariosRepository.findByEmpresasModelId(empresaid, pageable);
    }

    public Page<UsuariosModel> getByEmpresaAndEstado(Long empresaid, Boolean estado, Pageable pageable) {
        return usuariosRepository.findByEmpresasModelIdAndEstado(empresaid, estado, pageable);
    }

    public UsuariosModel createUsuario(UsuariosModel trabajadoresModel){
        return usuariosRepository.save(trabajadoresModel);
    }

    public UsuariosModel editUsuario(UsuariosModel trabajadoresModel, Long id){
        Optional<UsuariosModel> existing = usuariosRepository.findById(id);
        if (existing.isPresent()){
            UsuariosModel trabajador = existing.get();
            trabajador.setNombre(trabajadoresModel.getNombre());
            trabajador.setApellido(trabajadoresModel.getApellido());
            trabajador.setDni(trabajadoresModel.getDni());
            trabajador.setEstado(trabajadoresModel.getEstado());
            trabajador.setUsername(trabajadoresModel.getUsername());
            trabajador.setPassword(trabajadoresModel.getPassword());
            trabajador.setRolesModel(trabajadoresModel.getRolesModel());
            return usuariosRepository.save(trabajador);
        }
        return null;
    }

    public void deleteUsuario(Long id){
        usuariosRepository.deleteById(id);
    }
}
