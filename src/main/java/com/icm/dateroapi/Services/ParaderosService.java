package com.icm.dateroapi.Services;

import com.icm.dateroapi.Models.EmpresasModel;
import com.icm.dateroapi.Models.ParaderosModel;
import com.icm.dateroapi.Repositories.ParaderosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParaderosService {
    @Autowired
    ParaderosRepository paraderosRepository;

    public List<ParaderosModel> GetAll(){
        return paraderosRepository.findAll();
    }

    public Optional<ParaderosModel> GetById(Long id){
        return paraderosRepository.findById(id);
    }

    public List<ParaderosModel> getByEstado(Boolean estado) {
        return paraderosRepository.findByEstado(estado);
    }
    /*
    public List<ParaderosModel> getByEmpresasAndEstado(Long empresaid, Boolean estado) {
        return paraderosRepository.findByEmpresasModelIdAndEstado(empresaid, estado);
    }
    */

    public ParaderosModel CreateParaderos(ParaderosModel paraderosModel){
        return paraderosRepository.save(paraderosModel);
    }

    public ParaderosModel EditParaderos(ParaderosModel paraderosModel, Long id){
        Optional<ParaderosModel> existing = paraderosRepository.findById(id);
        if (existing.isPresent()){
            ParaderosModel paraderos = existing.get();
            paraderos.setNombre(paraderosModel.getNombre());
            paraderos.setEstado(paraderosModel.getEstado());
            //paraderos.setDistritosModel(paraderosModel.getDistritosModel());
            paraderos.setLatitud(paraderosModel.getLatitud());
            paraderos.setLongitud(paraderosModel.getLongitud());
            return paraderosRepository.save(paraderos);
        } else {
            return null;
        }
    }

    public void DeleteParadero(Long id){
        paraderosRepository.deleteById(id);
    }
}
