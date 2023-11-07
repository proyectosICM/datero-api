package com.icm.dateroapi.Services;

import com.icm.dateroapi.Models.BusesModel;
import com.icm.dateroapi.Models.UsuariosModel;
import com.icm.dateroapi.Repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<UsuariosModel> getByEmpresa(Long empresaid) {
        return usuariosRepository.findByEmpresasModelId(empresaid);
    }

    public List<UsuariosModel> getByEmpresaAndEstado(Long empresaid, Boolean estado) {
        return usuariosRepository.findByEmpresasModelIdAndEstado(empresaid, estado);
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
