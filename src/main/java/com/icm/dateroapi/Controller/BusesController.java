package com.icm.dateroapi.Controller;

import com.icm.dateroapi.Models.BusesModel;
import com.icm.dateroapi.Services.BusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/buses")
public class BusesController {
    @Autowired
    private BusesService busesService;

    @GetMapping
    public List<BusesModel> GetAll(){
        return busesService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusesModel> GetAllId(@PathVariable Long id){
        Optional<BusesModel> buses = busesService.getById(id);
        if (buses.isPresent()){
            return new ResponseEntity<>(buses.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/xempresa/{empresaid}")
    public List<BusesModel> GetxEmpresa(@PathVariable Long empresaid){
        return busesService.getByEmpresa(empresaid);
    }

    @GetMapping("/xempresaAndEstado/{empresaid}/{estado}")
    public List<BusesModel> GetxEmpresaAndEstado(@PathVariable Long empresaid, @PathVariable Boolean estado){
        return busesService.getByEmpresaAndEstado(empresaid, estado);
    }

    @PostMapping
    public ResponseEntity<BusesModel> CrearB(@RequestBody BusesModel busesModel){
        BusesModel cbus = busesService.createBus(busesModel);
        return new ResponseEntity<>(cbus,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusesModel> EditarB(@RequestBody BusesModel busesModel, @PathVariable Long id){
        BusesModel ebus = busesService.editBus(busesModel, id);
        if (ebus!=null){
            return new ResponseEntity<>(ebus, HttpStatus.OK);
        }
        return null;
    }

    @PutMapping("pos/{id}")
    public ResponseEntity<BusesModel> EditarPos(@RequestBody BusesModel busesModel, @PathVariable Long id){
        BusesModel ebus = busesService.editarPosicionamiento(busesModel, id);
        if (ebus!=null){
            return new ResponseEntity<>(ebus, HttpStatus.OK);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BusesModel> EliminarB(@PathVariable Long id){
        busesService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
