package com.icm.dateroapi.Services;

import com.icm.dateroapi.Models.ConteoBoletosModel;
import com.icm.dateroapi.Models.DistritosModel;
import com.icm.dateroapi.Repositories.ConteoBoletosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConteoBoletosService {
    @Autowired
    ConteoBoletosRepository conteoBoletosRepository;

    public List<ConteoBoletosModel> GetAll(){
        return conteoBoletosRepository.findAll();
    }

    public Optional<ConteoBoletosModel> GetById(Long id){
        return conteoBoletosRepository.findById(id);
    }
    public ConteoBoletosModel CreateConteoB(ConteoBoletosModel conteoBoletosModel){
        return conteoBoletosRepository.save(conteoBoletosModel);
    }

    public ConteoBoletosModel EditConteoB(ConteoBoletosModel conteoBoletosModel, Long id){
        Optional<ConteoBoletosModel> existing = conteoBoletosRepository.findById(id);
        if (existing.isPresent()){
            ConteoBoletosModel conteoB = existing.get();
            conteoB.setConteo(conteoBoletosModel.getConteo());
            conteoB.setDia(conteoBoletosModel.getDia());
            conteoB.setBoletosModel(conteoBoletosModel.getBoletosModel());
            return conteoBoletosRepository.save(conteoB);
        } else {
            return null;
        }
    }

    public void DeleteById(Long id){
        conteoBoletosRepository.deleteById(id);
    }
}
