package com.prueba.nttdata.cliente.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.nttdata.cliente.models.Cliente;
import com.prueba.nttdata.cliente.models.Persona;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateCliente() throws Exception {
        // Crear objetos de prueba
        Persona persona = new Persona();
        persona.setNombre("Test User");
        persona.setGenero("M");
        persona.setEdad(30);
        persona.setIdentificacion("123456789");
        persona.setDireccion("Test Address");
        persona.setTelefono("987654321");

        Cliente cliente = new Cliente();
        cliente.setContrasenia("test123");
        cliente.setEstado(true);
        cliente.setPersona(persona);

        // Convertir el objeto a JSON
        String clienteJson = objectMapper.writeValueAsString(cliente);

        // Realizar la solicitud POST y verificar la respuesta
        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clienteJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Cliente creado con exito"))
                .andExpect(jsonPath("$.data.estado").value(true));
    }
}