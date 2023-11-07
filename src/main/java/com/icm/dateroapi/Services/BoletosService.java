package com.icm.dateroapi.Services;

import com.icm.dateroapi.Models.BoletosModel;
import com.icm.dateroapi.Models.BusesModel;
import com.icm.dateroapi.Repositories.BoletosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoletosService {
    @Autowired
    BoletosRepository boletosRepository;
    public List<BoletosModel> GetAll(){
        return boletosRepository.findAll();
    }

    public Optional<BoletosModel> GetById(Long id){
        return boletosRepository.findById(id);
    }
    public List<BoletosModel> getByEmpresaandRuta(Long empresaid, Long rutaid) {
        return boletosRepository.findByEmpresasModelIdAndRutasModelId(empresaid, rutaid);
    }

    public BoletosModel CreateBoleto(BoletosModel busesModel){
        return boletosRepository.save(busesModel);
    }

    public BoletosModel EditBoleto(BoletosModel boletosModel, Long id){
        Optional<BoletosModel> existing = boletosRepository.findById(id);
        if (existing.isPresent()){
            BoletosModel boleto = existing.get();
            boleto.setNombre(boletosModel.getNombre());
            boleto.setValor(boletosModel.getValor());
            boleto.setRutasModel(boletosModel.getRutasModel());

            return boletosRepository.save(boleto);
        }
        return null;
    }

    public void DeleteBoleto(Long id){
        boletosRepository.deleteById(id);
    }
}
