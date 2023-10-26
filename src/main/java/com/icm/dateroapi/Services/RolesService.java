package com.icm.dateroapi.Services;

import com.icm.dateroapi.Models.ParaderosModel;
import com.icm.dateroapi.Models.RolesModel;
import com.icm.dateroapi.Repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
public class RolesService {
    @Autowired
    RolesRepository rolesRepository;

    public List<RolesModel> GetAll(){
        return rolesRepository.findAll();
    }

    public Optional<RolesModel> GetById(Long id){
        return rolesRepository.findById(id);
    }
    public List<RolesModel> getByEstado(Boolean estado) {
        return rolesRepository.findByEstado(estado);
    }

    public RolesModel CreateRoles(RolesModel rolesModel){
        return rolesRepository.save(rolesModel);
    }

    public RolesModel EditRoles(RolesModel rolesModel, Long id){
        Optional<RolesModel> existing = rolesRepository.findById(id);
        if (existing.isPresent()){
            RolesModel roles = existing.get();
            roles.setNombre(rolesModel.getNombre());
            roles.setEstado(rolesModel.getEstado());
            return rolesRepository.save(roles);
        }
        return null;
    }

    public void DeleteRoles(Long id){
        rolesRepository.deleteById(id);
    }
}
