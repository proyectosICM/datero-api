package com.icm.dateroapi.Controller;

import com.icm.dateroapi.Models.BoletosModel;
import com.icm.dateroapi.Models.TiempoRutaModel;
import com.icm.dateroapi.Services.BoletosService;
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
    BoletosService boletosService;

    @GetMapping
    public List<BoletosModel> GetAll(){
        return boletosService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletosModel> GetBId(@PathVariable Long id){
        Optional<BoletosModel> boleto = boletosService.GetById(id);
        if (boleto.isPresent()){
            return new ResponseEntity<>(boleto.get(), HttpStatus.OK);
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<BoletosModel> CrearB(@RequestBody BoletosModel boletosModel){
        BoletosModel cboleto = boletosService.CreateBoleto(boletosModel);
        return new ResponseEntity<>(cboleto, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BoletosModel> EditarB(@RequestBody BoletosModel boletosModel, @PathVariable Long id){
        BoletosModel eboleto = boletosService.EditBoleto(boletosModel, id);
        if (eboleto!=null){
            return new ResponseEntity<>(eboleto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BoletosModel> EliminarB(@PathVariable Long id){
        boletosService.DeleteBoleto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
