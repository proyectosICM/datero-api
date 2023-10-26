package com.icm.dateroapi.Controller;

import com.icm.dateroapi.Models.BusesModel;
import com.icm.dateroapi.Models.UsuariosModel;
import com.icm.dateroapi.Services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/usuarios")
public class UsuariosController {
    @Autowired
    UsuariosService usuariosService;

    @GetMapping
    public List<UsuariosModel> GetAllT(){
        return usuariosService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosModel> GetAllTID(@PathVariable Long id){
        Optional<UsuariosModel> trabajador = usuariosService.GetById(id);
        if (trabajador!=null){
            return new ResponseEntity<>(trabajador.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/xempresa/{empresaid}")
    public List<UsuariosModel> GetxEmpresa(@PathVariable Long empresaid){
        return usuariosService.getByEmpresa(empresaid);
    }

    @GetMapping("/xempresaAndEstado/{empresaid}/{estado}")
    public List<UsuariosModel> GetxEmpresaAndEstado(@PathVariable Long empresaid, @PathVariable Boolean estado){
        return usuariosService.getByEmpresaAndEstado(empresaid, estado);
    }

    @PostMapping
    public ResponseEntity<UsuariosModel> CrearT(@RequestBody UsuariosModel trabajadoresModel){
        UsuariosModel ctrabajador = usuariosService.CreateUsuario(trabajadoresModel);
        return new ResponseEntity<>(ctrabajador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuariosModel> EditarT(@RequestBody UsuariosModel trabajadoresModel, @PathVariable Long id){
        UsuariosModel etrabajador = usuariosService.EditUsuario(trabajadoresModel, id);
        if (etrabajador!=null){
            return new ResponseEntity<>(etrabajador, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuariosModel> EliminarT(@PathVariable Long id){
        usuariosService.DeleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
