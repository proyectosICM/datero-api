package com.icm.dateroapi.services;

import com.icm.dateroapi.models.*;
import com.icm.dateroapi.repositories.ConteoBoletosRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
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

    public List<ConteoBoletosModel> findLast7DaysRecords() {
        LocalDate today = LocalDate.now();  // Obtener la fecha actual

        // Calcular la fecha hace 7 días
        LocalDate oneWeekAgo = today.minusDays(7);

        // Realizar la consulta JPA para buscar registros en los últimos 7 días
        List<ConteoBoletosModel> records = entityManager.createQuery(
                        "SELECT cb FROM ConteoBoletosModel cb WHERE cb.dia >= :oneWeekAgo AND cb.dia <= :today",
                        ConteoBoletosModel.class
                )
                .setParameter("oneWeekAgo", oneWeekAgo)
                .setParameter("today", today)
                .getResultList();

        return records;
    }

    public List<Map<String, Object>> findLast7DaysRecordsOrdered(Long busId) {
        LocalDate today = LocalDate.now();
        LocalDate sixDaysAgo = today.minusDays(6);

        List<Object[]> resultList = entityManager.createQuery(
                        "SELECT cb.dia, cb.busesModel.placa, cb.boletosModel.nombre, cb.boletosModel.valor, SUM(cb.conteo), SUM(cb.totalAcumulado) " +
                                "FROM ConteoBoletosModel cb " +
                                "WHERE cb.dia >= :sixDaysAgo AND cb.dia <= :today " +
                                "AND cb.busesModel.id = :busId " +  // Agregamos la condición para el ID del vehículo
                                "GROUP BY cb.dia, cb.busesModel.placa, cb.boletosModel.nombre, cb.boletosModel.valor",
                        Object[].class
                )
                .setParameter("sixDaysAgo", sixDaysAgo)
                .setParameter("today", today)
                .setParameter("busId", busId)  // Pasamos el parámetro del ID del vehículo
                .getResultList();

        List<Map<String, Object>> responseList = new ArrayList<>();

        for (Object[] result : resultList) {
            LocalDate dia = (LocalDate) result[0];
            String placa = (String) result[1];
            String nombreBoleto = (String) result[2];
            String valorBoleto = (String) result[3];
            Long conteo = (Long) result[4];
            Double totalAcumulado = (Double) result[5];

            // Create a map for the response structure
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("dia", new int[]{dia.getYear(), dia.getMonthValue(), dia.getDayOfMonth()});
            response.put("placa", placa);

            Map<String, Object> servicio = new LinkedHashMap<>();
            servicio.put("nombre", nombreBoleto);
            servicio.put("valor", valorBoleto);
            servicio.put("conteo", conteo);
            servicio.put("totalAcumulado", totalAcumulado);

            response.put("servicios", Collections.singletonList(servicio));

            responseList.add(response);
        }

        return responseList;
    }

    public List<Map<String, Object>> findRecordsInCurrentMonth(Long busId) {
        YearMonth currentYearMonth = YearMonth.now();
        LocalDate firstDayOfMonth = currentYearMonth.atDay(1);
        LocalDate lastDayOfMonth = currentYearMonth.atEndOfMonth();

        List<Object[]> resultList = entityManager.createQuery(
                        "SELECT cb.dia, cb.busesModel.placa, cb.boletosModel.nombre, cb.boletosModel.valor, SUM(cb.conteo), SUM(cb.totalAcumulado) " +
                                "FROM ConteoBoletosModel cb " +
                                "WHERE cb.dia >= :firstDayOfMonth AND cb.dia <= :lastDayOfMonth " +
                                "AND cb.busesModel.id = :busId " +  // Agregamos la condición para el ID del vehículo
                                "GROUP BY cb.dia, cb.busesModel.placa, cb.boletosModel.nombre, cb.boletosModel.valor",
                        Object[].class
                )
                .setParameter("firstDayOfMonth", firstDayOfMonth)
                .setParameter("lastDayOfMonth", lastDayOfMonth)
                .setParameter("busId", busId)  // Pasamos el parámetro del ID del vehículo
                .getResultList();

        List<Map<String, Object>> responseList = new ArrayList<>();

        for (Object[] result : resultList) {
            LocalDate dia = (LocalDate) result[0];
            String placa = (String) result[1];
            String nombreBoleto = (String) result[2];
            String valorBoleto = (String) result[3];
            Long conteo = (Long) result[4];
            Double totalAcumulado = (Double) result[5];

            // Create a map for the response structure
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("dia", new int[]{dia.getYear(), dia.getMonthValue(), dia.getDayOfMonth()});
            response.put("placa", placa);

            Map<String, Object> servicio = new LinkedHashMap<>();
            servicio.put("nombre", nombreBoleto);
            servicio.put("valor", valorBoleto);
            servicio.put("conteo", conteo);
            servicio.put("totalAcumulado", totalAcumulado);

            response.put("servicios", Collections.singletonList(servicio));

            responseList.add(response);
        }

        return responseList;
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
