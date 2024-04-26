package com.icm.dateroapi.services;

import com.icm.dateroapi.models.DistritosModel;
import com.icm.dateroapi.repositories.DistritosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistritosService {
    @Autowired
    private DistritosRepository distritosRepository;

    public List<DistritosModel> getAll(){
        return distritosRepository.findAll();
    }

    public Optional<DistritosModel> getById(Long id){
        return distritosRepository.findById(id);
    }
    public List<DistritosModel> getByEstado(Boolean estado) {
        return distritosRepository.findByEstado(estado);
    }
    public DistritosModel createDistrito(DistritosModel distritosModel){
        return distritosRepository.save(distritosModel);
    }

    public DistritosModel editDistrito(DistritosModel distritosModel, Long id){
        Optional<DistritosModel> existing = distritosRepository.findById(id);
        if (existing.isPresent()){
            DistritosModel distrito = existing.get();
            distrito.setNombre(distritosModel.getNombre());
            distrito.setEstado(distritosModel.getEstado());
            return distritosRepository.save(distrito);
        } else {
            return null;
        }
    }

    public void deleteById(Long id){
        distritosRepository.deleteById(id);
    }
}
