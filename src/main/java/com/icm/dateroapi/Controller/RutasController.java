package com.icm.dateroapi.Controller;

import com.icm.dateroapi.Models.RolesModel;
import com.icm.dateroapi.Models.RutasModel;
import com.icm.dateroapi.Services.RutasService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<RutasModel> GetxEmpresa(@PathVariable Long empresaid){
        return rutasService.getByEmpresa(empresaid);
    }

    @GetMapping("/xempresaAndEstado/{empresaid}/{estado}")
    public List<RutasModel> GetxEstado(@PathVariable Long empresaid,@PathVariable Boolean estado){
        return rutasService.getByEmpresaAndEstado(empresaid, estado);
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
