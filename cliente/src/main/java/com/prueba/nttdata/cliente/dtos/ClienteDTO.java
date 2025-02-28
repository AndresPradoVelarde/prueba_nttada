package com.prueba.nttdata.cliente.dtos;

import java.io.Serializable;

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long clienteId;
    private String contrasenia;
    private Boolean estado;
    private PersonaDTO persona;

    public ClienteDTO(Long clienteId, String contrasenia, Boolean estado, PersonaDTO persona) {
        this.clienteId = clienteId;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.persona = persona;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    
    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public PersonaDTO getPersona() {
        return persona;
    }
    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }
}
