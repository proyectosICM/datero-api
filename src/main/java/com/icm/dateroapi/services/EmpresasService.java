package com.icm.dateroapi.services;

import com.icm.dateroapi.models.EmpresasModel;
import com.icm.dateroapi.repositories.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresasService {
    @Autowired
    private EmpresasRepository empresasRepository;

    public List<EmpresasModel> getAll() {
        return empresasRepository.findAll();
    }

    public Optional<EmpresasModel> getById(Long id) {
        return empresasRepository.findById(id);
    }
    public List<EmpresasModel> getByEstado(Boolean estado) {
        return empresasRepository.findByEstado(estado);
    }
    public EmpresasModel createEmpresa(EmpresasModel empresasModel){
        return empresasRepository.save(empresasModel);
    }

    public EmpresasModel editEmpresa(EmpresasModel empresasModel, Long id){
        Optional<EmpresasModel> existing = empresasRepository.findById(id);
        if (existing.isPresent()){
            EmpresasModel empresa = existing.get();
            empresa.setNombre(empresasModel.getNombre());
            empresa.setEstado(empresasModel.getEstado());
            return empresasRepository.save(empresa);
        } else {
            return null;
        }
    }

    public void deleteEmpresa(Long id){
        empresasRepository.deleteById(id);
    }

}
