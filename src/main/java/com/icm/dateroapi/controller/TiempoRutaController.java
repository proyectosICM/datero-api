package com.icm.dateroapi.controller;

import com.icm.dateroapi.models.TiempoRutaModel;
import com.icm.dateroapi.services.TiempoRutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tiemporuta")
public class TiempoRutaController {
    @Autowired
    private TiempoRutaService tiempoRutaService;

    @GetMapping
    public List<TiempoRutaModel> GetAll(){
        return tiempoRutaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiempoRutaModel> GetTRId(@PathVariable Long id){
        Optional<TiempoRutaModel> rutas = tiempoRutaService.getById(id);
        if (rutas.isPresent()){
            return new ResponseEntity<>(rutas.get(), HttpStatus.OK);
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<TiempoRutaModel> CrearTR(@RequestBody TiempoRutaModel tiempoRutaModel){
        TiempoRutaModel ctiemporuta = tiempoRutaService.createTiempoRuta(tiempoRutaModel);
        return new ResponseEntity<>(ctiemporuta, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TiempoRutaModel> EditarTR(@RequestBody TiempoRutaModel tiempoRutaModel, @PathVariable Long id){
        TiempoRutaModel etiemporuta = tiempoRutaService.editTiempoRuta(tiempoRutaModel, id);
        if (etiemporuta!=null){
            return new ResponseEntity<>(etiemporuta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TiempoRutaModel> EliminarTR(@PathVariable Long id){
        tiempoRutaService.deleteTiempoRuta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
