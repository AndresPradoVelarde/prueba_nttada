package com.prueba.nttdata.cliente.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.nttdata.cliente.dtos.ApiResponse;
import com.prueba.nttdata.cliente.messaging.publisher.ClienteEventPublisher;
import com.prueba.nttdata.cliente.models.Cliente;
import com.prueba.nttdata.cliente.models.Persona;
import com.prueba.nttdata.cliente.services.ClienteService;
import com.prueba.nttdata.cliente.services.PersonaService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    PersonaService personaService;
    @Autowired
    ClienteService clienteService;
    @Autowired
    ClienteEventPublisher clienteEventPublisher;

    @PostMapping
    public ResponseEntity<?> postCreateCliente(@RequestBody Cliente cliente) {
        Persona persona = new Persona();
        Cliente nuevoCliente = cliente;

        if (nuevoCliente.getPersona().getPersonaId() != null) {
            persona = personaService.findById(nuevoCliente.getPersona());
        } else {
            persona = personaService.save(nuevoCliente.getPersona());
        }

        nuevoCliente.setPersona(persona);
        nuevoCliente = clienteService.save(nuevoCliente);

        clienteEventPublisher.publishClienteCreado(nuevoCliente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Cliente creado con exito", nuevoCliente));
    }

    @GetMapping
    public ResponseEntity<?> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes = clienteService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Lista de clientes consultado con exito", clientes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientesById(@PathVariable Long id) {
        Cliente cliente = new Cliente();
        cliente = clienteService.findById(id);
        if(cliente != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Cliente encontrado con exito", cliente));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el cliente", cliente));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putAUpdateClienteById(@PathVariable Long id, @RequestBody Cliente cliente) {

        Persona personaEncontrada = null;
        if(cliente.getPersona() != null && cliente.getPersona().getPersonaId() != null){
            personaEncontrada = new Persona();
            personaEncontrada = personaService.findById(cliente.getPersona().getPersonaId());
        }

        if (personaEncontrada == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No se ha encontrado la persona", null));

        Cliente clienteEncontrado = new Cliente();
        clienteEncontrado = clienteService.findById(id);
        if (clienteEncontrado == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el cliente", null));

        personaEncontrada.setIdentificacion(cliente.getPersona().getIdentificacion());
        personaEncontrada.setNombre(cliente.getPersona().getNombre());
        personaEncontrada.setGenero(cliente.getPersona().getGenero());
        personaEncontrada.setEdad(cliente.getPersona().getEdad());
        personaEncontrada.setTelefono(cliente.getPersona().getTelefono());
        personaService.save(personaEncontrada);

        clienteEncontrado.setPersona(personaEncontrada);
        clienteEncontrado.setContrasenia(cliente.getContrasenia());
        clienteEncontrado.setEstado(cliente.getEstado());
        clienteService.save(clienteEncontrado);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Cliente actualizado con exito", clienteEncontrado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        if (cliente == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el cliente", null));

        Persona persona = cliente.getPersona();
        if(persona != null){
            clienteService.deleteById(cliente.getClienteId());
            personaService.deleteById(persona.getPersonaId());
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Cliente eliminado con exito", null));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No se ha podido eliminar el cliente", null));
        } 
    }
}