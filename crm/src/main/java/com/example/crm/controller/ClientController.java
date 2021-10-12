package com.example.crm.controller;

import com.example.crm.converter.ClientConverter;
import com.example.crm.dto.basic.BasicResponse;
import com.example.crm.dto.client.CreateClientRequest;
import com.example.crm.dto.client.DeleteClientRequest;
import com.example.crm.dto.client.UpdateClientRequest;
import com.example.crm.entity.Client;
import com.example.crm.service.ClientService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Slf4j
@Api(tags = "Client Management")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping(consumes="application/json")
    public ResponseEntity<BasicResponse> createClients(@Valid @RequestBody List<CreateClientRequest> requests) {
        List<Client> clients;

        try {
            clients = clientService.createClients(ClientConverter.toEntity(requests));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(BasicResponse.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(BasicResponse.success(clients));
    }

    @GetMapping
    public ResponseEntity<BasicResponse> getClients() {
        List<Client> clients;

        try {
            clients = clientService.getClients();
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(BasicResponse.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(BasicResponse.success(clients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasicResponse> getClient(@PathVariable Long id) {
        Client client;

        try {
            client = clientService.getClient(id);
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(BasicResponse.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(BasicResponse.success(client));
    }

    @PutMapping(consumes="application/json")
    public ResponseEntity<BasicResponse> updateClient(@Valid @RequestBody UpdateClientRequest request) {
        Client client;

        try {
            client = clientService.updateClient(ClientConverter.toEntity(request));
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(BasicResponse.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(BasicResponse.success(client));
    }

    @DeleteMapping(consumes="application/json")
    public ResponseEntity<BasicResponse> deleteClient(@Valid  @RequestBody DeleteClientRequest request) {
        String result;

        try {
             result = clientService.deleteClient(request.getId());
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(BasicResponse.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(BasicResponse.success(result));
    }
}
