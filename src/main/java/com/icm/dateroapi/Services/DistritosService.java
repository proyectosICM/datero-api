package com.icm.dateroapi.Services;

import com.icm.dateroapi.Models.BusesModel;
import com.icm.dateroapi.Models.DistritosModel;
import com.icm.dateroapi.Repositories.DistritosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistritosService {
    @Autowired
    DistritosRepository distritosRepository;

    public List<DistritosModel> GetAll(){
        return distritosRepository.findAll();
    }

    public Optional<DistritosModel> GetById(Long id){
        return distritosRepository.findById(id);
    }
    public List<DistritosModel> getByEstado(Boolean estado) {
        return distritosRepository.findByEstado(estado);
    }
    public DistritosModel CreateDistrito(DistritosModel distritosModel){
        return distritosRepository.save(distritosModel);
    }

    public DistritosModel EditDistrito(DistritosModel distritosModel, Long id){
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

    public void DeleteById(Long id){
        distritosRepository.deleteById(id);
    }
}
