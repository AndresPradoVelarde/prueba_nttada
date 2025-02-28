package com.prueba.nttdata.cliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.nttdata.cliente.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
