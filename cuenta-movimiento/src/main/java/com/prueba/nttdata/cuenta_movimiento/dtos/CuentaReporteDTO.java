package com.prueba.nttdata.cuenta_movimiento.dtos;

import java.util.List;

public class CuentaReporteDTO {
    
    private Long cuentaId;
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private boolean estado;
    private List<MovimientoReporteDTO> movimientos;
    
    public CuentaReporteDTO(Long cuentaId, String numeroCuenta, String tipoCuenta, double saldoInicial, boolean estado,
            List<MovimientoReporteDTO> movimientos) {
        this.cuentaId = cuentaId;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.movimientos = movimientos;
    }

    public CuentaReporteDTO() {
    }

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<MovimientoReporteDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoReporteDTO> movimientos) {
        this.movimientos = movimientos;
    }
    
    
}
