package com.icm.dateroapi.Services;

import com.icm.dateroapi.Models.*;
import com.icm.dateroapi.Repositories.ConteoBoletosRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConteoBoletosService {
    @Autowired
    private ConteoBoletosRepository conteoBoletosRepository;
    @Autowired
    private EntityManager entityManager;

    public List<ConteoBoletosModel> getAll(){
        return conteoBoletosRepository.findAll();
    }

    public Optional<ConteoBoletosModel> getById(Long id){
        return conteoBoletosRepository.findById(id);
    }

    public List<ConteoBoletosModel> obtenerConteoPorBusIdYFechaActual(Long busId) {
        LocalDate fechaActualPeru = obtenerFechaActualPeru();

        TypedQuery<ConteoBoletosModel> query = entityManager.createQuery(
                "SELECT c FROM ConteoBoletosModel c " +
                        "WHERE c.busesModel.id = :busId " +
                        "AND c.dia = :fechaActualPeru",
                ConteoBoletosModel.class
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

    public ConteoBoletosModel aumentarBoleto(ConteoBoletosModel conteoBoletosModel) {
        Long busId = conteoBoletosModel.getBusesModel().getId();
        Long boletoId = conteoBoletosModel.getBoletosModel().getId();
        List<ConteoBoletosModel> registros = obtenerConteoPorBusIdYFechaActual(busId);

        ConteoBoletosModel nuevoRegistro;

        if (registros.isEmpty()) {
            nuevoRegistro = crearNuevoRegistro(conteoBoletosModel, busId, boletoId);
        } else {
            List<ConteoBoletosModel> registrosFiltrados = registros.stream()
                    .filter(registro -> registro.getBoletosModel().getId().equals(boletoId))
                    .collect(Collectors.toList());

            if (registrosFiltrados.isEmpty()) {
                nuevoRegistro = crearNuevoRegistro(conteoBoletosModel, busId, boletoId);
            } else {
                nuevoRegistro = actualizarRegistroExistente(registrosFiltrados.get(0), conteoBoletosModel);
            }
        }

        return nuevoRegistro;
    }

    private ConteoBoletosModel crearNuevoRegistro(ConteoBoletosModel conteoBoletosModel, Long busId, Long boletoId) {
        BusesModel bus = new BusesModel();
        bus.setId(busId);

        EmpresasModel empresa = new EmpresasModel();
        empresa.setId(conteoBoletosModel.getEmpresasModel().getId());

        BoletosModel boleto = new BoletosModel();
        boleto.setId(boletoId);

        ConteoBoletosModel nuevoRegistro = new ConteoBoletosModel();
        nuevoRegistro.setBusesModel(bus);
        nuevoRegistro.setEmpresasModel(empresa);
        nuevoRegistro.setBoletosModel(boleto);
        nuevoRegistro.setDia(obtenerFechaActualPeru());
        nuevoRegistro.setConteo(1);
        nuevoRegistro.setTotalAcumulado(conteoBoletosModel.getTotalAcumulado());

        return createConteoB(nuevoRegistro);
    }

    private ConteoBoletosModel actualizarRegistroExistente(ConteoBoletosModel registroExistente, ConteoBoletosModel conteoBoletosModel) {
        int nuevoConteo = registroExistente.getConteo() + 1;
        registroExistente.setConteo(nuevoConteo);
        registroExistente.setTotalAcumulado(registroExistente.getTotalAcumulado() + conteoBoletosModel.getTotalAcumulado());
        return editConteoB(registroExistente, registroExistente.getId());
    }

    public ConteoBoletosModel createConteoB(ConteoBoletosModel conteoBoletosModel){
        return conteoBoletosRepository.save(conteoBoletosModel);
    }

    public ConteoBoletosModel editConteoB(ConteoBoletosModel conteoBoletosModel, Long id){
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

    public void deleteById(Long id){
        conteoBoletosRepository.deleteById(id);
    }
}
