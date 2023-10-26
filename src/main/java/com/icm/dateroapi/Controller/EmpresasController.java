package com.icm.dateroapi.Controller;


import com.icm.dateroapi.Models.DistritosModel;
import com.icm.dateroapi.Models.EmpresasModel;
import com.icm.dateroapi.Services.EmpresasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/empresas")
public class EmpresasController {
    @Autowired
    EmpresasService empresasService;

    @GetMapping
    public List<EmpresasModel> GetAllE(){
        return empresasService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresasModel> GetEID(@PathVariable Long id){
        Optional<EmpresasModel> empresa = empresasService.GetById(id);
        if (empresa.isPresent()){
            return new ResponseEntity<>(empresa.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/xestado/{estado}")
    public List<EmpresasModel> GetxEstado(@PathVariable Boolean estado){
        return empresasService.getByEstado(estado);
    }

    @PostMapping
    public ResponseEntity<EmpresasModel> CrearE(@RequestBody EmpresasModel empresasModel){
        EmpresasModel cempresa = empresasService.CreateEmpresa(empresasModel);
        return new ResponseEntity<>(cempresa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresasModel> EditarE(@RequestBody EmpresasModel empresasModel, @PathVariable Long id){
        EmpresasModel eempresa = empresasService.EditEmpresa(empresasModel, id);
        if (eempresa!=null){
            return new ResponseEntity<>(eempresa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresasModel> EliminarE(@PathVariable Long id){
        empresasService.DeleteEmpresa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
