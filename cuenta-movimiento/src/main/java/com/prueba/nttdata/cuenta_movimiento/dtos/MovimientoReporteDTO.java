package com.prueba.nttdata.cuenta_movimiento.dtos;

import java.time.LocalDateTime;

public class MovimientoReporteDTO {
    
    private Long movimientoId;
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldo;
    
    public MovimientoReporteDTO(Long movimientoId, LocalDateTime fecha, String tipoMovimiento, double valor,
            double saldo) {
        this.movimientoId = movimientoId;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
    }

    public MovimientoReporteDTO() {
    }

    public Long getMovimientoId() {
        return movimientoId;
    }

    public void setMovimientoId(Long movimientoId) {
        this.movimientoId = movimientoId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    
}
