package com.prueba.nttdata.cliente.dtos;

import java.io.Serializable;

public class ClienteMensajeDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    private Long clienteId;
    private String nombre;
    
    public ClienteMensajeDTO(Long clienteId, String nombre) {
        this.clienteId = clienteId;
        this.nombre = nombre;
    }

    public ClienteMensajeDTO() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
