package com.example.crm.service;

import com.example.crm.entity.Client;
import com.example.crm.exception.BizException;
import com.example.crm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "Client")
public class ClientService {

    @Autowired
    ClientRepository clientRepo;

    @Autowired
    CompanyService companyService;

    public List<Client> getClients() {
        return clientRepo.findAll();
    }

    @Cacheable(key = "#id")
    public Client getClient(Long id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new BizException("Not found Client"));
    }

    public Client createClient(Client client) {
        return clientRepo.save(client);
    }

    public List<Client> createClients(List<Client> clients) {
        return clientRepo.saveAll(clients);
    }

    @CachePut(key = "#client.id")
    public Client updateClient(Client client) {
        Client currentClient = clientRepo.findById(client.getId())
                        .orElseThrow(() -> new BizException("Not found Client"));
        currentClient.setEmail(client.getEmail());
        currentClient.setName(client.getName());
        currentClient.setPhone(client.getPhone());
        currentClient.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        currentClient.setUpdatedBy("aaa");

        return clientRepo.save(currentClient);
    }

    @CacheEvict(key = "#id")
    public String deleteClient(Long id) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new BizException("Not found Client"));
        clientRepo.delete(client);

        return "deleted";
    }
}
