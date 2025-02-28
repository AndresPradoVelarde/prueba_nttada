package com.prueba.nttdata.cuenta_movimiento.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.nttdata.cuenta_movimiento.dtos.ApiResponse;
import com.prueba.nttdata.cuenta_movimiento.dtos.EstadoCuentaDTO;
import com.prueba.nttdata.cuenta_movimiento.services.ReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    ReporteService reporteService;

    @GetMapping
    public ResponseEntity<?> getAllCuentas(@RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        if (fechaInicio.isAfter(fechaFin)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),
                    "La fecha de inicio no puede ser posterior a la fecha de fin", null));
        }

        EstadoCuentaDTO reporte = reporteService.generarEstadoCuenta(clienteId, fechaInicio, fechaFin);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Reporte generado con exito con éxito", reporte));
    }

}
