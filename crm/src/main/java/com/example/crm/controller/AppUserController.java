package com.example.crm.controller;

import com.example.crm.converter.UserConverter;
import com.example.crm.dto.user.AddRoleToUserRequest;
import com.example.crm.dto.user.CreateUserRequest;
import com.example.crm.dto.user.DeleteUserRequest;
import com.example.crm.dto.user.UpdateUserRequest;
import com.example.crm.entity.AppUser;
import com.example.crm.service.AppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
@Api(tags = "User Management")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @ApiOperation(value = "Create App User")
    @PostMapping(consumes="application/json")
    public ResponseEntity<AppUser> createUser(@Valid  @RequestBody CreateUserRequest request) {
        AppUser user;

        try {
            user = appUserService.createAppUser(UserConverter.toEntity(request));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> getUsers() {
        List<AppUser> users;

        try {
            users = appUserService.getAppUsers();
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUser(@PathVariable Long id) {
        AppUser user;

        try {
            user = appUserService.getAppUser(id);
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(user);
    }

    @PutMapping(consumes="application/json")
    public ResponseEntity<AppUser> updateUser(@Valid @RequestBody UpdateUserRequest request) {
        AppUser user;

        try {
            user = appUserService.updateAppUser(UserConverter.toEntity(request));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(user);
    }

    @DeleteMapping(consumes="application/json")
    public ResponseEntity<String> deleteUser(@Valid @RequestBody DeleteUserRequest request) {
        String result;

        try {
            result = appUserService.deleteAppUser(request.getId());
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>("Delete failed", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "add-role", consumes="application/json")
    public ResponseEntity<AppUser> addRole(@Valid @RequestBody AddRoleToUserRequest request) {
        AppUser user;

        try {
            user = appUserService.addRole(request.getUserId(), request.getRoleId());
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(user);
    }
}
