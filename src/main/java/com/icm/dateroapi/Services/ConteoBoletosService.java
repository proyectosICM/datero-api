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
    ConteoBoletosRepository conteoBoletosRepository;

    @Autowired
    private EntityManager entityManager;

    public List<ConteoBoletosModel> GetAll(){
        return conteoBoletosRepository.findAll();
    }

    public Optional<ConteoBoletosModel> GetById(Long id){
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
        // Establece la zona horaria de Perú (UTC-5)
        ZoneId zonaPeru = ZoneId.of("America/Lima");

        // Obtiene la fecha y hora actual en la zona horaria de Perú
        ZonedDateTime fechaHoraPeru = ZonedDateTime.now(zonaPeru);

        // Extrae la fecha de la fecha y hora actual
        LocalDate fechaActualPeru = fechaHoraPeru.toLocalDate();

        return fechaActualPeru;
    }

    public ConteoBoletosModel AumentarBoleto(ConteoBoletosModel conteoBoletosModel) {
        Long busId = conteoBoletosModel.getBusesModel().getId();
        Long boletoId = conteoBoletosModel.getBoletosModel().getId();
        // Obtén la lista de registros para el día actual y el bus específico
        List<ConteoBoletosModel> registros = obtenerConteoPorBusIdYFechaActual(busId);

        BusesModel bus = new BusesModel();
        bus.setId(busId);

        EmpresasModel empresa = new EmpresasModel();
        empresa.setId(conteoBoletosModel.getEmpresasModel().getId());

        BoletosModel boleto = new BoletosModel();
        boleto.setId(conteoBoletosModel.getBoletosModel().getId());

        if (registros.isEmpty()) {
            // No hay registros para el día actual, crea uno nuevo
            ConteoBoletosModel nuevoRegistro = new ConteoBoletosModel();
            nuevoRegistro.setBusesModel(bus);
            nuevoRegistro.setEmpresasModel(empresa);
            nuevoRegistro.setBoletosModel(boleto);
            nuevoRegistro.setDia(obtenerFechaActualPeru());
            nuevoRegistro.setConteo(1);
            nuevoRegistro.setTotalAcumulado(conteoBoletosModel.getTotalAcumulado());
            return CreateConteoB(nuevoRegistro);
        } else {
            // Ya existe un registro para el día actual y el bus específico
            // Filtra la lista de registros por el ID del boleto
            List<ConteoBoletosModel> registrosFiltrados = registros.stream()
                    .filter(registro -> registro.getBoletosModel().getId().equals(boletoId))
                    .collect(Collectors.toList());

            if (registrosFiltrados.isEmpty()) {
                // No existe un registro para el boleto específico, crea uno nuevo
                ConteoBoletosModel nuevoRegistro = new ConteoBoletosModel();
                nuevoRegistro.setBusesModel(bus);
                nuevoRegistro.setEmpresasModel(empresa);
                nuevoRegistro.setBoletosModel(boleto);
                nuevoRegistro.setDia(obtenerFechaActualPeru());
                nuevoRegistro.setConteo(1); // Inicializa el conteo en 1
                nuevoRegistro.setTotalAcumulado(conteoBoletosModel.getTotalAcumulado());
                return CreateConteoB(nuevoRegistro);
            } else {
                // Ya existe un registro para el boleto específico, aumenta el boleto en el registro existente
                ConteoBoletosModel registroExistente = registrosFiltrados.get(0);
                int nuevoConteo = registroExistente.getConteo() + 1;
                registroExistente.setConteo(nuevoConteo);
                registroExistente.setTotalAcumulado(registroExistente.getTotalAcumulado() + conteoBoletosModel.getTotalAcumulado());
                return EditConteoB(registroExistente, registroExistente.getId());
            }
        }
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
