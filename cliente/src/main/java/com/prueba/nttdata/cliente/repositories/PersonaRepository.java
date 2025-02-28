package com.prueba.nttdata.cliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.nttdata.cliente.models.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
}
