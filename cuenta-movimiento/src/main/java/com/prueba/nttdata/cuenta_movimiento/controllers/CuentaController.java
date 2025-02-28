package com.prueba.nttdata.cuenta_movimiento.controllers;

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

import com.prueba.nttdata.cuenta_movimiento.dtos.ApiResponse;
import com.prueba.nttdata.cuenta_movimiento.models.Cuenta;
import com.prueba.nttdata.cuenta_movimiento.services.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<?> postCreateCuenta(@RequestBody Cuenta cuenta) {
        Cuenta nuevaCuenta = new Cuenta();

        nuevaCuenta = cuentaService.save(cuenta);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Cuenta creada con exito", nuevaCuenta));
    }

    @GetMapping
    public ResponseEntity<?> getAllCuentas() {
        List<Cuenta> cuentas = cuentaService.findAll();
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Lista de cuentas consultada con éxito", cuentas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCuentaById(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.findById(id);
        
        if (cuenta != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(HttpStatus.OK.value(), "Cuenta encontrada con éxito", cuenta));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No se ha encontrado la cuenta", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        Cuenta cuentaExistente = cuentaService.findById(id);
        
        if (cuentaExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No se ha encontrado la cuenta", null));
        }
        
        cuentaExistente.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaExistente.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaExistente.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaExistente.setEstado(cuenta.getEstado());
        

        if (cuenta.getClienteId() != null) {
            cuentaExistente.setClienteId(cuenta.getClienteId());
        }
        
        if (cuenta.getNombreCliente() != null) {
            cuentaExistente.setNombreCliente(cuenta.getNombreCliente());
        }
        
        Cuenta cuentaActualizada = cuentaService.save(cuentaExistente);
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Cuenta actualizada con éxito", cuentaActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.findById(id);
        
        if (cuenta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No se ha encontrado la cuenta", null));
        }
        
        cuentaService.deleteById(id);
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Cuenta eliminada con éxito", null));
    }
    
}
