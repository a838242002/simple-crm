package com.example.crm.service;

import com.example.crm.entity.Role;
import com.example.crm.exception.AuthException;
import com.example.crm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepo;

    public List<Role> getRoles() {
        return roleRepo.findAll();
    }

    public Role getRole(Long id) {
        return roleRepo.findById(id)
                .orElseThrow(() -> new AuthException("Not found Role"));
    }

    public Role createRole(Role role) {
        return roleRepo.save(role);
    }

    public Role updateRole(Role role) {
        Role currentRole = roleRepo.findById(role.getId())
                .orElseThrow(() -> new AuthException("Not found Role"));
        currentRole.setRoleName(role.getRoleName());

        return roleRepo.save(currentRole);
    }

    public String deleteRole(Long id) {
        Role role = roleRepo.findById(id)
                .orElseThrow(() -> new AuthException("Not found Role"));

        roleRepo.delete(role);

        return "deleted";
    }
}
