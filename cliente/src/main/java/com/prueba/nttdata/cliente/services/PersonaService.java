package com.prueba.nttdata.cliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.nttdata.cliente.models.Persona;
import com.prueba.nttdata.cliente.repositories.PersonaRepository;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona findById(Persona persona) {
        Persona nuevaPersona = persona;
        persona = personaRepository.findById(persona.getPersonaId()).orElseGet(
                () -> {
                    return personaRepository.save(nuevaPersona);
                });
        return persona;
    }

    public Persona findById(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        personaRepository.deleteById(id);
    }
}
