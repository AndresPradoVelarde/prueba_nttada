package com.prueba.nttdata.cuenta_movimiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba.nttdata.cuenta_movimiento.exceptions.SaldoNoDisponibleException;
import com.prueba.nttdata.cuenta_movimiento.exceptions.IllegalArgumentException;
import com.prueba.nttdata.cuenta_movimiento.models.Cuenta;
import com.prueba.nttdata.cuenta_movimiento.models.Movimiento;
import com.prueba.nttdata.cuenta_movimiento.repositories.MovimientoRepository;

@Service
public class MovimientoService {
    
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaService cuentaService;

    public List<Movimiento> findAll() {
        return movimientoRepository.findAll();
    }

    public Movimiento findById(Long id) {
        return movimientoRepository.findById(id).orElse(null);
    }


    public Movimiento save(Movimiento movimiento) {

        if (movimiento.getCuenta() == null || movimiento.getCuenta().getCuentaId() == null) {
            throw new IllegalArgumentException("Se debe especificar una cuenta para el movimiento");
        }
        
        Cuenta cuenta = cuentaService.findById(movimiento.getCuenta().getCuentaId());
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta especificada no existe");
        }
        
        if (movimiento.getValor() < 0) {
            double montoRetiro = Math.abs(movimiento.getValor());
            if (cuenta.getSaldoInicial() < montoRetiro) {
                throw new SaldoNoDisponibleException("Saldo no disponible");
            }
        }
        
        double saldoActual = cuenta.getSaldoInicial();
        double nuevoSaldo = saldoActual + movimiento.getValor();
        
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaService.save(cuenta);
        
        movimiento.setCuenta(cuenta);
        
        movimiento.setSaldo(nuevoSaldo);
        
        return movimientoRepository.save(movimiento);
    }

    public void deleteById(Long id) {
        movimientoRepository.deleteById(id);
    }
    
}
