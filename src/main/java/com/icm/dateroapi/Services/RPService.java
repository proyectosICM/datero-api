package com.icm.dateroapi.Services;

import com.icm.dateroapi.Models.BusesModel;
import com.icm.dateroapi.Models.RPModel;
import com.icm.dateroapi.Repositories.RPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RPService {
    @Autowired
    RPRepository rpRepository;

    public List<RPModel> ListarRP(){
        return rpRepository.findAll();
    }

    public Optional<RPModel> ListarRPXID(Long id){
        return rpRepository.findById(id);
    }

    public List<RPModel> getByRutasId(Long rutaid) {
        return rpRepository.findByRutasModelId(rutaid);
    }

    public RPModel CrearRP(RPModel rpModel){
        return rpRepository.save(rpModel);
    }

    public RPModel EditarRP(RPModel rpModel, Long id){
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

    public void EliminarRP(Long id){
        rpRepository.deleteById(id);
    }
}
