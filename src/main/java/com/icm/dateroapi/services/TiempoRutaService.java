package com.icm.dateroapi.services;

import com.icm.dateroapi.models.TiempoRutaModel;
import com.icm.dateroapi.repositories.TiempoRutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TiempoRutaService {
    @Autowired
    private TiempoRutaRepository tiempoRutaRepository;

    public List<TiempoRutaModel> getAll(){
        return tiempoRutaRepository.findAll();
    }

    public Optional<TiempoRutaModel> getById(Long id){
        return tiempoRutaRepository.findById(id);
    }

    public TiempoRutaModel createTiempoRuta(TiempoRutaModel tiemporutaModel){
        return tiempoRutaRepository.save(tiemporutaModel);
    }

    public TiempoRutaModel editTiempoRuta(TiempoRutaModel tiemporutaModel, Long id){
        Optional<TiempoRutaModel> existing = tiempoRutaRepository.findById(id);
        if (existing.isPresent()){
            TiempoRutaModel tiemporuta = existing.get();
            tiemporuta.setPaseEsperado(tiemporutaModel.getPaseEsperado());
            tiemporuta.setHoraPase(tiemporutaModel.getHoraPase());
            tiemporuta.setRutasModel(tiemporutaModel.getRutasModel());
            tiemporuta.setBusesModel(tiemporutaModel.getBusesModel());
            tiemporuta.setParaderosModel(tiemporutaModel.getParaderosModel());
            return tiempoRutaRepository.save(tiemporuta);
        }
        return null;
    }

    public void deleteTiempoRuta(Long id){
        tiempoRutaRepository.deleteById(id);
    }
}
