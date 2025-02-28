package com.prueba.nttdata.cliente.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prueba.nttdata.cliente.models.Cliente;
import com.prueba.nttdata.cliente.models.Persona;
import com.prueba.nttdata.cliente.repositories.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente clienteTest;
    private Persona personaTest;

    @BeforeEach
    void setUp() {
        personaTest = new Persona();
        personaTest.setPersonaId(1L);
        personaTest.setNombre("Jose Lema");
        personaTest.setGenero("M");
        personaTest.setEdad(35);
        personaTest.setIdentificacion("098254785");
        personaTest.setDireccion("Otavalo sn y principal");
        personaTest.setTelefono("098254785");

        clienteTest = new Cliente();
        clienteTest.setClienteId(1L);
        clienteTest.setContrasenia("1234");
        clienteTest.setEstado(true);
        clienteTest.setPersona(personaTest);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteTest);
    }

    @Test
    @DisplayName("Test para guardar un cliente")
    void testSaveCliente() {
        Cliente clienteGuardado = clienteService.save(clienteTest);
        
        assertNotNull(clienteGuardado, "El cliente no es nulo");
        assertEquals(1L, clienteGuardado.getClienteId(), "El ID es 1");
    }

}
