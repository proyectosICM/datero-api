package com.icm.dateroapi.Services;

import com.icm.dateroapi.Models.RolesModel;
import com.icm.dateroapi.Models.RutasModel;
import com.icm.dateroapi.Repositories.RutasRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<RutasModel> getByEmpresa(Long empresaid) {
        return rutasRepository.findByEmpresasModelId(empresaid);
    }
    public List<RutasModel> getByEmpresaAndEstado(Long empresaid, Boolean estado) {
        return rutasRepository.findByEmpresasModelIdAndEstado(empresaid, estado);
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
