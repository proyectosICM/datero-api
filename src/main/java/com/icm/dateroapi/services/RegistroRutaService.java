package com.icm.dateroapi.services;

import com.icm.dateroapi.models.*;
import com.icm.dateroapi.repositories.RegistroRutaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class RegistroRutaService {
    @Autowired
    private RegistroRutaRepository registroRutaRepository;

    @Autowired
    private EntityManager entityManager;

    public List<RegistroRutaModel> getAll(){
        return registroRutaRepository.findAll();
    }

    public Optional<RegistroRutaModel> getById(Long id){
        return registroRutaRepository.findById(id);
    }

    public List<RegistroRutaModel> obtenerRegistrosPorBusYFechaActual(Long busId) {
        LocalDate fechaActualPeru = obtenerFechaActualPeru();

        TypedQuery<RegistroRutaModel> query = entityManager.createQuery(
                "SELECT r FROM RegistroRutaModel r " +
                        "WHERE r.busesModel.id = :busId " +
                        "AND r.dia = :fechaActualPeru",
                RegistroRutaModel.class
        );
        query.setParameter("busId", busId);
        query.setParameter("fechaActualPeru", fechaActualPeru);

        return query.getResultList();
    }

    private LocalDate obtenerFechaActualPeru() {
        ZoneId zonaPeru = ZoneId.of("America/Lima");
        ZonedDateTime fechaHoraPeru = ZonedDateTime.now(zonaPeru);
        LocalDate fechaActualPeru = fechaHoraPeru.toLocalDate();
        return fechaActualPeru;
    }

    public RegistroRutaModel createRegistroRuta(RegistroRutaModel paraderosModel){
        return registroRutaRepository.save(paraderosModel);
    }

    public RegistroRutaModel agregarPasoRuta(RegistroRutaModel registroRutaModel){
        Long busId = registroRutaModel.getBusesModel().getId();
        List<RegistroRutaModel> registro = obtenerRegistrosPorBusYFechaActual(busId);

        EmpresasModel empresa = new EmpresasModel();
        empresa.setId(registroRutaModel.getEmpresasModel().getId());

        RutasModel rutas = new RutasModel();
        rutas.setId(registroRutaModel.getRutasModel().getId());

        BusesModel bus = new BusesModel();
        bus.setId(busId);

        ParaderosModel paradero = new ParaderosModel();
        paradero.setId(registroRutaModel.getParaderosModel().getId());

        LocalTime horaActual = LocalTime.now();
        Time horaLlegada = Time.valueOf(horaActual);

        RegistroRutaModel nuevoregistro = new RegistroRutaModel();
        nuevoregistro.setEmpresasModel(empresa);
        nuevoregistro.setRutasModel(rutas);
        nuevoregistro.setBusesModel(bus);
        nuevoregistro.setParaderosModel(paradero);
        nuevoregistro.setDia(obtenerFechaActualPeru());
        nuevoregistro.setHoraLlegada(horaLlegada);
        nuevoregistro.setHoraEsperada(horaLlegada);
        return createRegistroRuta(nuevoregistro);
    }

    /*
        if (registro.isEmpty()){

        }
        */

    public RegistroRutaModel editRegistroRuta(RegistroRutaModel paraderosModel, Long id){
        Optional<RegistroRutaModel> existing = registroRutaRepository.findById(id);
        if (existing.isPresent()){
            RegistroRutaModel registro = existing.get();
            registro.setEmpresasModel(paraderosModel.getEmpresasModel());
            registro.setRutasModel(paraderosModel.getRutasModel());
            registro.setBusesModel(paraderosModel.getBusesModel());
            registro.setParaderosModel(paraderosModel.getParaderosModel());
            registro.setHoraEsperada(paraderosModel.getHoraEsperada());
            registro.setHoraLlegada(paraderosModel.getHoraLlegada());
            return registroRutaRepository.save(registro);
        } else {
            return null;
        }
    }

    public void deleteRegistroRuta(Long id){
        registroRutaRepository.deleteById(id);
    }
}
