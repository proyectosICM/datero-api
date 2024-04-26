package com.icm.dateroapi.services;

import com.icm.dateroapi.models.BoletosModel;
import com.icm.dateroapi.repositories.BoletosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoletosService {
    @Autowired
    private BoletosRepository boletosRepository;

    public List<BoletosModel> getAll() {
        return boletosRepository.findAll();
    }

    public Optional<BoletosModel> getById(Long id) {
        return boletosRepository.findById(id);
    }

    public List<BoletosModel> getByEmpresaAndRuta(Long empresaId, Long rutaId) {
        return boletosRepository.findByEmpresasModelIdAndRutasModelId(empresaId, rutaId);
    }

    public BoletosModel createBoleto(BoletosModel boleto) {
        return boletosRepository.save(boleto);
    }

    public BoletosModel editBoleto(BoletosModel boleto, Long id) {
        Optional<BoletosModel> existing = boletosRepository.findById(id);
        if (existing.isPresent()) {
            BoletosModel existingBoleto = existing.get();
            existingBoleto.setNombre(boleto.getNombre());
            existingBoleto.setValor(boleto.getValor());
            existingBoleto.setRutasModel(boleto.getRutasModel());
            return boletosRepository.save(existingBoleto);
        }
        return null;
    }

    public void deleteBoleto(Long id) {
        boletosRepository.deleteById(id);
    }
}