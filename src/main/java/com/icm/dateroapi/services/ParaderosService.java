package com.icm.dateroapi.services;

import com.icm.dateroapi.models.ParaderosModel;
import com.icm.dateroapi.repositories.ParaderosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParaderosService {
    @Autowired
    private ParaderosRepository paraderosRepository;

    public List<ParaderosModel> getAll(){
        return paraderosRepository.findAll();
    }

    public Page<ParaderosModel> findAll(Pageable pageable) {
        return paraderosRepository.findAll(pageable);
    }

    public Optional<ParaderosModel> getById(Long id){
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

    public Page<ParaderosModel> getByEstado(Boolean estado, Pageable pageable) {
        return paraderosRepository.findByEstado(estado, pageable);
    }

    public ParaderosModel createParaderos(ParaderosModel paraderosModel){
        return paraderosRepository.save(paraderosModel);
    }

    public ParaderosModel editParaderos(ParaderosModel paraderosModel, Long id){
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

    public void deleteParadero(Long id){
        paraderosRepository.deleteById(id);
    }
}
