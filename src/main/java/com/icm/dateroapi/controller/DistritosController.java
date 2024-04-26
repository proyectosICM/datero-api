package com.icm.dateroapi.controller;

import com.icm.dateroapi.models.DistritosModel;
import com.icm.dateroapi.services.DistritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/distritos")
public class DistritosController {
    @Autowired
    private DistritosService distritosService;

    @GetMapping
    public List<DistritosModel> GetAllD(){
        return distritosService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistritosModel> GetDId(@PathVariable Long id){
        Optional<DistritosModel> distrito = distritosService.getById(id);
        if(distrito.isPresent()){
            return new ResponseEntity<>(distrito.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/xestado/{estado}")
    public List<DistritosModel> GetxEstado(@PathVariable Boolean estado){
        return distritosService.getByEstado(estado);
    }

    @PostMapping
    public ResponseEntity<DistritosModel> CrearD(@RequestBody DistritosModel distritosModel){
        DistritosModel cdistrito = distritosService.createDistrito(distritosModel);
        return new ResponseEntity<>(cdistrito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistritosModel> EditarD(@RequestBody DistritosModel distritosModel, @PathVariable Long id){
        DistritosModel edistrito = distritosService.editDistrito(distritosModel, id);
        if (edistrito!=null){
            return new ResponseEntity<>(edistrito, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DistritosModel> EliminarD(@PathVariable Long id){
        distritosService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
