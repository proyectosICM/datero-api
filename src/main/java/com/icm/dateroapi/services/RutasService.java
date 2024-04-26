package com.icm.dateroapi.services;

import com.icm.dateroapi.models.RutasModel;
import com.icm.dateroapi.repositories.RutasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RutasService {
    @Autowired
    private RutasRepository rutasRepository;


    public List<RutasModel> getAll(){
        return rutasRepository.findAll();
    }
    public Optional<RutasModel> getById(Long id){
        return rutasRepository.findById(id);
    }
    public Page<RutasModel> getByEmpresa(Long empresaid, Pageable pageable) {
        return rutasRepository.findByEmpresasModelId(empresaid, pageable);
    }
    public Page<RutasModel> getByEmpresaAndEstado(Long empresaid, Boolean estado, Pageable pageable) {
        return rutasRepository.findByEmpresasModelIdAndEstado(empresaid, estado, pageable);
    }
    public RutasModel createRuta(RutasModel rutasModel){
        return rutasRepository.save(rutasModel);
    }

    public RutasModel editRuta(RutasModel rutasModel, Long id){
        Optional<RutasModel> existing = rutasRepository.findById(id);
        if(existing.isPresent()){
            RutasModel rutas = existing.get();
            rutas.setNombre(rutasModel.getNombre());
            rutas.setEstado(rutasModel.getEstado());
            return rutasRepository.save(rutas);
        }
        return null;
    }

    public void deleteRuta(Long id){
        rutasRepository.deleteById(id);
    }

}
