package com.icm.dateroapi.controller;

import com.icm.dateroapi.models.UsuariosModel;
import com.icm.dateroapi.services.UsuariosService;
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
@RequestMapping("api/usuarios")
public class UsuariosController {
    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    public List<UsuariosModel> GetAllT(){
        return usuariosService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosModel> GetAllTID(@PathVariable Long id){
        Optional<UsuariosModel> trabajador = usuariosService.getById(id);
        if (trabajador!=null){
            return new ResponseEntity<>(trabajador.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/xempresa/{empresaid}")
    public Page<UsuariosModel> GetxEmpresa(@PathVariable Long empresaid,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "6") int size){
        Pageable pageable = PageRequest.of(page, size);
        return usuariosService.getByEmpresa(empresaid, pageable);
    }

    @GetMapping("/xempresaAndEstado/{empresaid}/{estado}")
    public Page<UsuariosModel> GetxEmpresaAndEstado(@PathVariable Long empresaid,
                                                    @PathVariable Boolean estado,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "6") int size){

        Pageable pageable = PageRequest.of(page, size);
        return usuariosService.getByEmpresaAndEstado(empresaid, estado, pageable);
    }

    @PostMapping
    public ResponseEntity<UsuariosModel> CrearT(@RequestBody UsuariosModel trabajadoresModel){
        UsuariosModel ctrabajador = usuariosService.createUsuario(trabajadoresModel);
        return new ResponseEntity<>(ctrabajador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuariosModel> EditarT(@RequestBody UsuariosModel trabajadoresModel, @PathVariable Long id){
        UsuariosModel etrabajador = usuariosService.editUsuario(trabajadoresModel, id);
        if (etrabajador!=null){
            return new ResponseEntity<>(etrabajador, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuariosModel> EliminarT(@PathVariable Long id){
        usuariosService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}