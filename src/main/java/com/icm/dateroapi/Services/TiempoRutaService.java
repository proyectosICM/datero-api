package com.icm.dateroapi.Services;

import com.icm.dateroapi.Models.BusesModel;
import com.icm.dateroapi.Models.TiempoRutaModel;
import com.icm.dateroapi.Repositories.TiempoRutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TiempoRutaService {
    @Autowired
    TiempoRutaRepository tiempoRutaRepository;

    public List<TiempoRutaModel> GetAll(){
        return tiempoRutaRepository.findAll();
    }

    public Optional<TiempoRutaModel> GetById(Long id){
        return tiempoRutaRepository.findById(id);
    }

    public TiempoRutaModel CreateTiempoRuta(TiempoRutaModel tiemporutaModel){
        return tiempoRutaRepository.save(tiemporutaModel);
    }

    public TiempoRutaModel EditTiempoRuta(TiempoRutaModel tiemporutaModel, Long id){
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

    public void DeleteTiempoRuta(Long id){
        tiempoRutaRepository.deleteById(id);
    }
}
