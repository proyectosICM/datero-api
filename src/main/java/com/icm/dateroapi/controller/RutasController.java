package com.icm.dateroapi.controller;

import com.icm.dateroapi.models.RutasModel;
import com.icm.dateroapi.services.RutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/rutas")
public class RutasController {
    @Autowired
    private RutasService rutasService;

    @GetMapping
    public List<RutasModel> GetAll(){
        return rutasService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutasModel> GetRId(@PathVariable Long id){
        Optional<RutasModel> rutas = rutasService.getById(id);
        if (rutas.isPresent()){
            return new ResponseEntity<>(rutas.get(), HttpStatus.OK);
        }
        return null;
    }
    @GetMapping("/xempresa/{empresaid}")
    public Page<RutasModel> GetxEmpresa(@PathVariable Long empresaid,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "6") int size){
        Pageable pageable = PageRequest.of(page, size);
        return rutasService.getByEmpresa(empresaid, pageable);
    }

    @GetMapping("/xempresaAndEstado/{empresaid}/{estado}")
    public Page<RutasModel> GetxEstado(@PathVariable Long empresaid,
                                       @PathVariable Boolean estado,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return rutasService.getByEmpresaAndEstado(empresaid, estado, pageable);
    }

    @PostMapping
    public ResponseEntity<RutasModel> CrearR(@RequestBody RutasModel rutasModel){
        RutasModel crutas = rutasService.createRuta(rutasModel);
        return new ResponseEntity<>(crutas, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RutasModel> EditarR(@RequestBody RutasModel rutasModel, @PathVariable Long id){
        RutasModel eruta = rutasService.editRuta(rutasModel, id);
        if (eruta!=null){
            return new ResponseEntity<>(eruta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RutasModel> EliminarR(@PathVariable Long id){
        rutasService.deleteRuta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
