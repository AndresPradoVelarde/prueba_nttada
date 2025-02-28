package com.prueba.nttdata.cuenta_movimiento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.nttdata.cuenta_movimiento.models.Cuenta;
import com.prueba.nttdata.cuenta_movimiento.repositories.CuentaRepository;

@Service
public class CuentaService {
    
    @Autowired
    private CuentaRepository cuentaRepository;

    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        cuentaRepository.deleteById(id);
    }

}
