package com.icm.dateroapi.services;

import com.icm.dateroapi.models.RPModel;
import com.icm.dateroapi.repositories.RPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RPService {
    @Autowired
    private RPRepository rpRepository;

    public List<RPModel> listarRP(){
        return rpRepository.findAll();
    }

    public Optional<RPModel> listarRPXID(Long id){
        return rpRepository.findById(id);
    }

    public List<RPModel> getByRutasId(Long rutaid) {
        return rpRepository.findByRutasModelId(rutaid);
    }

    public RPModel crearRP(RPModel rpModel){
        return rpRepository.save(rpModel);
    }

    public RPModel editarRP(RPModel rpModel, Long id){
        Optional<RPModel> existing = rpRepository.findById(id);
        if (existing.isPresent()){
            RPModel rp = existing.get();
            rp.setRutasModel(rp.getRutasModel());
            rp.setParaderosModel(rpModel.getParaderosModel());
            rp.setOrden(rpModel.getOrden());
            rp.setEstado(rpModel.getEstado());
            return rpRepository.save(rp);
        }
        return null;
    }

    public void eliminarRP(Long id){
        rpRepository.deleteById(id);
    }
}
