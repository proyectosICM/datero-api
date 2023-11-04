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
import java.util.Optional;

@RestController
@RequestMapping("api/conteoB")
public class ConteoBoletosController {
    @Autowired
    ConteoBoletosService conteoBoletosService;

    @GetMapping
    public List<ConteoBoletosModel> GetAllCB(){
        return conteoBoletosService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConteoBoletosModel> GetCBId(@PathVariable Long id){
        Optional<ConteoBoletosModel> conteoB = conteoBoletosService.GetById(id);
        if(conteoB.isPresent()){
            return new ResponseEntity<>(conteoB.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ConteoBoletosModel> CrearCB(@RequestBody ConteoBoletosModel conteoBoletosModel){
        ConteoBoletosModel conteoB = conteoBoletosService.CreateConteoB(conteoBoletosModel);
        return new ResponseEntity<>(conteoB, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConteoBoletosModel> EditarCV(@RequestBody ConteoBoletosModel conteoBoletosModel, @PathVariable Long id){
        ConteoBoletosModel edistrito = conteoBoletosService.EditConteoB(conteoBoletosModel, id);
        if (edistrito!=null){
            return new ResponseEntity<>(edistrito, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConteoBoletosModel> EliminarCB(@PathVariable Long id){
        conteoBoletosService.DeleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
