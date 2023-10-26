package com.icm.dateroapi.Controller;


import com.icm.dateroapi.Models.EmpresasModel;
import com.icm.dateroapi.Models.ParaderosModel;
import com.icm.dateroapi.Services.ParaderosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/paraderos")
public class ParaderosController {
    @Autowired
    ParaderosService paraderosService;

    @GetMapping
    public List<ParaderosModel> ListarP(){
        return paraderosService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParaderosModel> ListarPId(@PathVariable Long id){
        Optional<ParaderosModel> paraderos = paraderosService.GetById(id);
        if (paraderos.isPresent()){
            return new ResponseEntity<>(paraderos.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/xestado/{estado}")
    public List<ParaderosModel> GetxEstado(@PathVariable Boolean estado){
        return paraderosService.getByEstado(estado);
    }
    /*
    @GetMapping("/xempresaAndEstado/{empresaid}/{estado}")
    public List<ParaderosModel> GetEmpresaxEstado(@PathVariable Long empresaid, @PathVariable Boolean estado){
        return paraderosService.getByEmpresasAndEstado(empresaid, estado);
    }
*/

    @PostMapping
    public ResponseEntity<ParaderosModel> CrearE(@RequestBody ParaderosModel paraderosModel){
        ParaderosModel cparaderos = paraderosService.CreateParaderos(paraderosModel);
        return new ResponseEntity<>(cparaderos, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParaderosModel> EditarE(@RequestBody ParaderosModel paraderosModel, @PathVariable Long id){
        ParaderosModel eparaderos = paraderosService.EditParaderos(paraderosModel, id);
        if (eparaderos!=null){
            return new ResponseEntity<>(eparaderos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ParaderosModel> EliminarE(@PathVariable Long id){
        paraderosService.DeleteParadero(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
