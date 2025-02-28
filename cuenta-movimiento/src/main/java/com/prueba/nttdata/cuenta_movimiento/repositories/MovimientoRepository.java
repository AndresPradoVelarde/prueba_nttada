package com.prueba.nttdata.cuenta_movimiento.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.nttdata.cuenta_movimiento.models.Cuenta;
import com.prueba.nttdata.cuenta_movimiento.models.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findByCuentaAndFechaBetween(Cuenta cuenta, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
