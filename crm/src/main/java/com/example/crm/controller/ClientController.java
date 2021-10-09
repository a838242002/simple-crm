package com.example.crm.controller;

import com.example.crm.converter.ClientConverter;
import com.example.crm.dto.client.CreateClientRequest;
import com.example.crm.dto.client.UpdateClientRequest;
import com.example.crm.entity.Client;
import com.example.crm.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Slf4j
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping()
    public ResponseEntity<List<Client>> createClients(@RequestBody List<CreateClientRequest> requests) {
        List<Client> clients;

        try {
            clients = clientService.createClients(ClientConverter.toEntity(requests));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(clients);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clients;

        try {
            clients = clientService.getClients();
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Client client;

        try {
            client = clientService.getClient(id);
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(client);
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody UpdateClientRequest request) {
        Client client;

        try {
            client = clientService.updateClient(ClientConverter.toEntity(request));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(client);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody Long id) {
        String result;

        try {
             result = clientService.deleteClient(id);
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>("Delete failed", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }
}