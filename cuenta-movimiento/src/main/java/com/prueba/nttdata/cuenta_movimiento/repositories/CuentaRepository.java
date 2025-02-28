package com.prueba.nttdata.cuenta_movimiento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.nttdata.cuenta_movimiento.models.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    
}
