package com.example.crm.controller;

import com.example.crm.converter.RoleConverter;
import com.example.crm.dto.role.CreateRoleRequest;
import com.example.crm.dto.role.UpdateRoleRequest;
import com.example.crm.entity.Role;
import com.example.crm.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Slf4j
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequest request) {
        Role role;

        try {
            role = roleService.createRole(RoleConverter.toEntity(request));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles;

        try {
            roles = roleService.getRoles();
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        Role role;

        try {
            role = roleService.getRole(id);
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(role);
    }

    @PutMapping
    public ResponseEntity<Role> updateRole(@RequestBody UpdateRoleRequest request) {
        Role role;

        try {
            role = roleService.updateRole(RoleConverter.toEntity(request));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(role);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRole(@RequestBody Long id) {
        String result;

        try {
            result = roleService.deleteRole(id);
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>("Delete failed", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }
}
