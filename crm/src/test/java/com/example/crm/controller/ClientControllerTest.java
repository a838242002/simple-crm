package com.example.crm.controller;

import com.example.crm.entity.Client;
import com.example.crm.entity.Company;
import com.example.crm.repository.ClientRepository;
import com.example.crm.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ClientService service;

    @Test
    public void getClient_success() throws Exception {
        Company company1 = new Company();
        company1.setId(1L);
        company1.setName("Company");
        company1.setAddress("Address X");

        Client client1 = new Client();
        client1.setCompany(company1);
        client1.setId(1L);
        client1.setName("Client 1");
        client1.setEmail("abc@abc.com");
        client1.setPhone("0912345678");

        Mockito.when(service.getClients()).thenReturn(List.of(client1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/clients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("Client 1")));
    }
}
