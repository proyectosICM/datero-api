package com.icm.dateroapi.controller;

import com.icm.dateroapi.models.BoletosModel;
import com.icm.dateroapi.services.BoletosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/boletos")
public class BoletosController {
    @Autowired
    private BoletosService boletosService;

    @GetMapping
    public List<BoletosModel> GetAll(){
        return boletosService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletosModel> GetBId(@PathVariable Long id){
        Optional<BoletosModel> boleto = boletosService.getById(id);
        if (boleto.isPresent()){
            return new ResponseEntity<>(boleto.get(), HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/xempresaAndRuta/{empresaid}/{ruta}")
    public List<BoletosModel> GetxEmpresaAndEstado(@PathVariable Long empresaid, @PathVariable Long ruta){
        return boletosService.getByEmpresaAndRuta(empresaid, ruta);
    }

    @PostMapping
    public ResponseEntity<BoletosModel> CrearB(@RequestBody BoletosModel boletosModel){
        BoletosModel cboleto = boletosService.createBoleto(boletosModel);
        return new ResponseEntity<>(cboleto, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BoletosModel> EditarB(@RequestBody BoletosModel boletosModel, @PathVariable Long id){
        BoletosModel eboleto = boletosService.editBoleto(boletosModel, id);
        if (eboleto!=null){
            return new ResponseEntity<>(eboleto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BoletosModel> EliminarB(@PathVariable Long id){
        boletosService.deleteBoleto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
