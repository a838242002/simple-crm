package com.example.crm.controller;

import com.example.crm.converter.RoleConverter;
import com.example.crm.dto.role.CreateRoleRequest;
import com.example.crm.dto.role.DeleteRoleRequest;
import com.example.crm.dto.role.UpdateRoleRequest;
import com.example.crm.entity.Role;
import com.example.crm.service.RoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Slf4j
@Api(tags = "Role Management", value = "")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping(consumes="application/json")
    public ResponseEntity<Role> createRole(@Valid @RequestBody CreateRoleRequest request) {
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

    @PutMapping(consumes="application/json")
    public ResponseEntity<Role> updateRole(@Valid @RequestBody UpdateRoleRequest request) {
        Role role;

        try {
            role = roleService.updateRole(RoleConverter.toEntity(request));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(role);
    }

    @DeleteMapping(consumes="application/json")
    public ResponseEntity<String> deleteRole(@Valid @RequestBody DeleteRoleRequest request) {
        String result;

        try {
            result = roleService.deleteRole(request.getId());
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>("Delete failed", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }
}
