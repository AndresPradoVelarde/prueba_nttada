package com.prueba.nttdata.cuenta_movimiento.dtos;

import java.time.LocalDate;
import java.util.List;

public class EstadoCuentaDTO {
    
    private Long clienteId;
    private String nombreCliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<CuentaReporteDTO> cuentas;
    
    public EstadoCuentaDTO(Long clienteId, String nombreCliente, LocalDate fechaInicio, LocalDate fechaFin,
            List<CuentaReporteDTO> cuentas) {
        this.clienteId = clienteId;
        this.nombreCliente = nombreCliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cuentas = cuentas;
    }

    public EstadoCuentaDTO() {
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<CuentaReporteDTO> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaReporteDTO> cuentas) {
        this.cuentas = cuentas;
    }
    
    
}
