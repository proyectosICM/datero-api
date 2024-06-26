package com.icm.dateroapi.services;

import com.icm.dateroapi.models.BusesModel;
import com.icm.dateroapi.repositories.BusesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusesService {
    @Autowired
    private BusesRepository busesRepository;

    public List<BusesModel> getAll() {
        return busesRepository.findAll();
    }

    public Optional<BusesModel> getById(Long id) {
        return busesRepository.findById(id);
    }
    public List<BusesModel> getByEmpresa(Long empresaid) {
        return busesRepository.findByEmpresasModelId(empresaid);
    }

    public List<BusesModel> getByEmpresaAndEstado(Long empresaid, Boolean estado) {
        return busesRepository.findByEmpresasModelIdAndEstado(empresaid, estado);
    }

    public Page<BusesModel> getByEmpresa(Long empresaid, Pageable pageable) {
        return busesRepository.findByEmpresasModelId(empresaid,  pageable);
    }

    public Page<BusesModel> getByEmpresaAndEstado(Long empresaid, Boolean estado, Pageable pageable) {
        return busesRepository.findByEmpresasModelIdAndEstado(empresaid, estado, pageable);
    }

    public BusesModel createBus(BusesModel busesModel) {
        return busesRepository.save(busesModel);
    }
    public BusesModel editBus(BusesModel busesModel, Long id){
        Optional<BusesModel> existing = busesRepository.findById(id);
        if (existing.isPresent()){
            BusesModel bus = existing.get();
            bus.setModelo(busesModel.getModelo());
            bus.setPlaca(busesModel.getPlaca());
            bus.setEstado(busesModel.getEstado());
            bus.setUsuariosModel(busesModel.getUsuariosModel());
            bus.setEmpresasModel(busesModel.getEmpresasModel());
            bus.setRutasModel(busesModel.getRutasModel());
            return busesRepository.save(bus);
        }
        return null;
    }
    public BusesModel editarPosicionamiento(BusesModel busesModel, Long id){
        Optional<BusesModel> existing = busesRepository.findById(id);
        if (existing.isPresent()){
            BusesModel bus = existing.get();
            bus.setLongitud(busesModel.getLongitud());
            bus.setLatitud(busesModel.getLatitud());
            return busesRepository.save(bus);
        }
        return null;
    }

    public void deleteById(Long id) {
        busesRepository.deleteById(id);
    }
}
