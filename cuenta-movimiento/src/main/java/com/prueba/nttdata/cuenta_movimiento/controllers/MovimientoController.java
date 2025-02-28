package com.prueba.nttdata.cuenta_movimiento.controllers;

import java.time.LocalDateTime;
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
import com.prueba.nttdata.cuenta_movimiento.models.Movimiento;
import com.prueba.nttdata.cuenta_movimiento.services.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<?> getAllMovimientos() {
        List<Movimiento> movimientos = movimientoService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Lista de movimientos consultada con éxito", movimientos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovimientoById(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.findById(id);
        
        if (movimiento != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(HttpStatus.OK.value(), "Movimiento encontrado con éxito", movimiento));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el movimiento", null));
        }
    }

    @PostMapping
    public ResponseEntity<?> createMovimiento(@RequestBody Movimiento movimiento) {
        if (movimiento.getFecha() == null) {
            movimiento.setFecha(LocalDateTime.now());
        }

        Movimiento nuevoMovimiento = movimientoService.save(movimiento);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Movimiento registrado con éxito",
                        nuevoMovimiento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        Movimiento movimientoExistente = movimientoService.findById(id);

        if (movimientoExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(),
                            "No se ha encontrado el movimiento", null));
        }

        movimiento.setMovimientoId(id);

        Movimiento movimientoActualizado = movimientoService.save(movimiento);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(),
                        "Movimiento actualizado con éxito", movimientoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovimiento(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.findById(id);
        
        if (movimiento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), 
                            "No se ha encontrado el movimiento", null));
        }
        
        movimientoService.deleteById(id);
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), 
                        "Movimiento eliminado con éxito", null));
    }

}
