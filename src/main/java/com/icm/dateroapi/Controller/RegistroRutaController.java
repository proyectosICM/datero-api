package com.icm.dateroapi.Controller;

import com.icm.dateroapi.Models.ConteoBoletosModel;
import com.icm.dateroapi.Models.RegistroRutaModel;
import com.icm.dateroapi.Services.ConteoBoletosService;
import com.icm.dateroapi.Services.RegistroRutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/registroRuta")
public class RegistroRutaController {
    @Autowired
    RegistroRutaService registroRutaService;

    @GetMapping
    public List<RegistroRutaModel> GetAllCB(){
        return registroRutaService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroRutaModel> GetRBId(@PathVariable Long id){
        Optional<RegistroRutaModel> conteoB = registroRutaService.GetById(id);
        if(conteoB.isPresent()){
            return new ResponseEntity<>(conteoB.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/registrosPorBusYFechaActual/{busId}")
    public List<RegistroRutaModel> obtenerRegistrosPorBusYFechaActual(@PathVariable Long busId) {
        return registroRutaService.obtenerRegistrosPorBusYFechaActual(busId);
    }

    @PostMapping("/agregarPasoRuta")
    public ResponseEntity<RegistroRutaModel> agregarPasoRuta(@RequestBody RegistroRutaModel registroRutaModel) {

            RegistroRutaModel nuevoRegistro = registroRutaService.agregarPasoRuta(registroRutaModel);
            return ResponseEntity.ok(nuevoRegistro);

    }

    @PostMapping
    public ResponseEntity<RegistroRutaModel> CrearRR(@RequestBody RegistroRutaModel registroRutaModel){
        RegistroRutaModel RR = registroRutaService.CreateRegistroRuta(registroRutaModel);
        return new ResponseEntity<>(RR, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroRutaModel> EditarRR(@RequestBody RegistroRutaModel registroRutaModel, @PathVariable Long id){
        RegistroRutaModel eRR = registroRutaService.EditRegistroRuta(registroRutaModel, id);
        if (eRR!=null){
            return new ResponseEntity<>(eRR, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegistroRutaModel> EliminarRR(@PathVariable Long id){
        registroRutaService.DeleteRegistroRuta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
