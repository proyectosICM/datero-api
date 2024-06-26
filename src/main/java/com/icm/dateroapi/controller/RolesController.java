package com.icm.dateroapi.controller;


import com.icm.dateroapi.models.RolesModel;
import com.icm.dateroapi.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/roles")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    @GetMapping
    public ResponseEntity<List<RolesModel>> listarRoles() {
        List<RolesModel> roles = rolesService.getAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesModel> listarRolPorId(@PathVariable("id") Long id) {
        Optional<RolesModel> rol = rolesService.getById(id);
        if (rol.isPresent()) {
            return new ResponseEntity<>(rol.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/xestado/{estado}")
    public List<RolesModel> GetxEstado(@PathVariable Boolean estado){
        return rolesService.getByEstado(estado);
    }

    @PostMapping
    public ResponseEntity<RolesModel> crearRol(@RequestBody RolesModel rolesModel) {
        RolesModel nuevoRol = rolesService.createRoles(rolesModel);
        return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolesModel> editarRol(@RequestBody RolesModel rolesModel, @PathVariable("id") Long id) {
        RolesModel rolEditado = rolesService.editRoles(rolesModel, id);
        if (rolEditado != null) {
            return new ResponseEntity<>(rolEditado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable("id") Long id) {
        rolesService.deleteRoles(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}