package com.prueba.nttdata.cuenta_movimiento.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cuenta")
public class Cuenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id", unique = true, nullable = false)
    private Long cuentaId;

    @Column(name = "numero_cuenta")
    @NotBlank(message = "El numeroCuenta es obligatorio")
    private String numeroCuenta;

    @Column(name = "tipo_cuenta")
    @NotBlank(message = "El tipoCuenta es obligatorio")
    private String tipoCuenta;

    @Column(name = "saldo_inicial")
    private double saldoInicial;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @OneToMany(mappedBy = "cuenta")
    private Set<Movimiento> movimientos;

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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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

    

}
