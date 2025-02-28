package com.prueba.nttdata.cuenta_movimiento.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.nttdata.cuenta_movimiento.dtos.CuentaReporteDTO;
import com.prueba.nttdata.cuenta_movimiento.dtos.EstadoCuentaDTO;
import com.prueba.nttdata.cuenta_movimiento.dtos.MovimientoReporteDTO;
import com.prueba.nttdata.cuenta_movimiento.exceptions.NotFoundException;
import com.prueba.nttdata.cuenta_movimiento.models.Cuenta;
import com.prueba.nttdata.cuenta_movimiento.models.Movimiento;
import com.prueba.nttdata.cuenta_movimiento.repositories.CuentaRepository;
import com.prueba.nttdata.cuenta_movimiento.repositories.MovimientoRepository;

@Service
public class ReporteService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    public EstadoCuentaDTO generarEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {

        List<Cuenta> cuentasCliente = findCuentasByClienteId(clienteId);

        if (cuentasCliente.isEmpty()) {
            throw new NotFoundException("No existe una cuenta con el id cliente: " + clienteId);
        }

        String nombreCliente = cuentasCliente.get(0).getNombreCliente();

        LocalDateTime fechaInicioDateTime = fechaInicio.atStartOfDay();
        LocalDateTime fechaFinDateTime = fechaFin.atTime(LocalTime.MAX);

        EstadoCuentaDTO reporte = new EstadoCuentaDTO();
        reporte.setClienteId(clienteId);
        reporte.setNombreCliente(nombreCliente);
        reporte.setFechaInicio(fechaInicio);
        reporte.setFechaFin(fechaFin);

        List<CuentaReporteDTO> cuentasReporte = new ArrayList<>();

        for (Cuenta cuenta : cuentasCliente) {
            List<Movimiento> movimientosCuenta = movimientoRepository.findByCuentaAndFechaBetween(cuenta,
                    fechaInicioDateTime, fechaFinDateTime);

            List<MovimientoReporteDTO> movimientosReporte = movimientosCuenta.stream()
                    .map(this::convertToMovimientoReporteDTO)
                    .collect(Collectors.toList());

            CuentaReporteDTO cuentaReporte = new CuentaReporteDTO(
                    cuenta.getCuentaId(),
                    cuenta.getNumeroCuenta(),
                    cuenta.getTipoCuenta(),
                    cuenta.getSaldoInicial(),
                    cuenta.getEstado(),
                    movimientosReporte);

            cuentasReporte.add(cuentaReporte);
        }

        reporte.setCuentas(cuentasReporte);

        return reporte;
    }

    private List<Cuenta> findCuentasByClienteId(Long clienteId) {
        return cuentaRepository.findAll().stream()
                .filter(cuenta -> clienteId.equals(cuenta.getClienteId()))
                .collect(Collectors.toList());
    }

    private MovimientoReporteDTO convertToMovimientoReporteDTO(Movimiento movimiento) {
        return new MovimientoReporteDTO(
                movimiento.getMovimientoId(),
                movimiento.getFecha(),
                movimiento.getTipoMovimiento(),
                movimiento.getValor(),
                movimiento.getSaldo());
    }

}
