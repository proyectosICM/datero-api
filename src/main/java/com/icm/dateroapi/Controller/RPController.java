package com.icm.dateroapi.Controller;


import com.icm.dateroapi.Models.RPModel;
import com.icm.dateroapi.Services.RPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/rp")
public class RPController {
    @Autowired
    private RPService rpService;

    @GetMapping
    public List<RPModel> ListarRP(){
        return rpService.listarRP();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RPModel> ListarRPId(@PathVariable Long id){
        Optional<RPModel> rp = rpService.listarRPXID(id);
        if (rp.isPresent()){
            return new ResponseEntity<>(rp.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("xruta/{ruta}")
    public List<RPModel> ListarPxR(@PathVariable("ruta") Long ruta){
        return rpService.getByRutasId(ruta);
    }

    @PostMapping
    public ResponseEntity<RPModel> CrearRP(@RequestBody RPModel rpModel){
        RPModel crp = rpService.crearRP(rpModel);
        return new ResponseEntity<>(crp, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RPModel> EditarRP(@RequestBody RPModel rpModel, @PathVariable Long id){
        RPModel erp = rpService.editarRP(rpModel, id);
        if (erp!=null){
            return new ResponseEntity<>(erp, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RPModel> EliminarRP(@PathVariable Long id){
        rpService.eliminarRP(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
