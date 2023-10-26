package com.icm.dateroapi.Controller;


import com.icm.dateroapi.Models.BusesModel;
import com.icm.dateroapi.Models.DistritosModel;
import com.icm.dateroapi.Services.DistritosService;
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
    DistritosService distritosService;

    @GetMapping
    public List<DistritosModel> GetAllD(){
        return distritosService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistritosModel> GetDId(@PathVariable Long id){
        Optional<DistritosModel> distrito = distritosService.GetById(id);
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
        DistritosModel cdistrito = distritosService.CreateDistrito(distritosModel);
        return new ResponseEntity<>(cdistrito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistritosModel> EditarD(@RequestBody DistritosModel distritosModel, @PathVariable Long id){
        DistritosModel edistrito = distritosService.EditDistrito(distritosModel, id);
        if (edistrito!=null){
            return new ResponseEntity<>(edistrito, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DistritosModel> EliminarD(@PathVariable Long id){
        distritosService.DeleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
