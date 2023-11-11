package com.icm.dateroapi.Controller;

import com.icm.dateroapi.Models.ConteoBoletosModel;
import com.icm.dateroapi.Models.DistritosModel;

import com.icm.dateroapi.Services.ConteoBoletosService;
import com.icm.dateroapi.Services.DistritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/conteoB")
public class ConteoBoletosController {
    @Autowired
    private ConteoBoletosService conteoBoletosService;

    @GetMapping
    public List<ConteoBoletosModel> getAllCB(){
        return conteoBoletosService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConteoBoletosModel> getCBId(@PathVariable Long id){
        Optional<ConteoBoletosModel> conteoB = conteoBoletosService.getById(id);
        if(conteoB.isPresent()){
            return new ResponseEntity<>(conteoB.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/last-7-days")
    public List<ConteoBoletosModel> findLast7DaysRecords() {
        return conteoBoletosService.findLast7DaysRecords();
    }

    @GetMapping("/last-7-days-ordered/{busId}")
    public List<Map<String, Object>> getLast7DaysRecordsOrdered(@PathVariable Long busId) {
        return conteoBoletosService.findLast7DaysRecordsOrdered(busId);
    }

    @GetMapping("/last-month-ordered/{busId}")
    public List<Map<String, Object>> getRecordsInCurrentMonth(@PathVariable Long busId) {
        return conteoBoletosService.findRecordsInCurrentMonth(busId);
    }

    @GetMapping("/conteoPorBusYFechaActual/{bus}")
    public List<ConteoBoletosModel> obtenerConteoPorBusIdYFechaActual(@PathVariable Long bus) {
        return conteoBoletosService.obtenerConteoPorBusIdYFechaActual(bus);
    }

    @PostMapping
    public ResponseEntity<ConteoBoletosModel> crearCB(@RequestBody ConteoBoletosModel conteoBoletosModel){
        ConteoBoletosModel conteoB = conteoBoletosService.createConteoB(conteoBoletosModel);
        return new ResponseEntity<>(conteoB, HttpStatus.CREATED);
    }

    @PostMapping("/aumentar")
    public ConteoBoletosModel aumentarBoleto(@RequestBody ConteoBoletosModel conteoBoletosModel) {
        return conteoBoletosService.aumentarBoleto(conteoBoletosModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConteoBoletosModel> editarCV(@RequestBody ConteoBoletosModel conteoBoletosModel, @PathVariable Long id){
        ConteoBoletosModel edistrito = conteoBoletosService.editConteoB(conteoBoletosModel, id);
        if (edistrito!=null){
            return new ResponseEntity<>(edistrito, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConteoBoletosModel> eliminarCB(@PathVariable Long id){
        conteoBoletosService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
