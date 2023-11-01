package com.icm.dateroapi.Controller;

import com.icm.dateroapi.Models.RutasModel;
import com.icm.dateroapi.Models.TiempoRutaModel;
import com.icm.dateroapi.Services.TiempoRutaService;
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
    TiempoRutaService tiempoRutaService;

    @GetMapping
    public List<TiempoRutaModel> GetAll(){
        return tiempoRutaService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiempoRutaModel> GetTRId(@PathVariable Long id){
        Optional<TiempoRutaModel> rutas = tiempoRutaService.GetById(id);
        if (rutas.isPresent()){
            return new ResponseEntity<>(rutas.get(), HttpStatus.OK);
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<TiempoRutaModel> CrearTR(@RequestBody TiempoRutaModel tiempoRutaModel){
        TiempoRutaModel ctiemporuta = tiempoRutaService.CreateTiempoRuta(tiempoRutaModel);
        return new ResponseEntity<>(ctiemporuta, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TiempoRutaModel> EditarTR(@RequestBody TiempoRutaModel tiempoRutaModel, @PathVariable Long id){
        TiempoRutaModel etiemporuta = tiempoRutaService.EditTiempoRuta(tiempoRutaModel, id);
        if (etiemporuta!=null){
            return new ResponseEntity<>(etiemporuta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TiempoRutaModel> EliminarTR(@PathVariable Long id){
        tiempoRutaService.DeleteTiempoRuta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
