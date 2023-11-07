package com.icm.dateroapi.Services;

import com.icm.dateroapi.Models.DistritosModel;
import com.icm.dateroapi.Models.EmpresasModel;
import com.icm.dateroapi.Repositories.EmpresasRepository;
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
