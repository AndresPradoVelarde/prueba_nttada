package com.prueba.nttdata.cliente.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "personas")
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id", unique = true, nullable = false)
    private Long personaId;

    @Column(name = "nombre")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @Column(name = "genero")
    private String genero;
    
    @Column(name = "edad")
    @Min(value = 18, message = "La edad debe ser mayor a 18")
    @Max(value = 200, message = "La edad debe ser menor a 200")
    private Integer edad;
    
    @Column(name = "identificacion")
    @NotBlank(message = "La identificacion es obligatoria")
    private String identificacion;
    
    @Column(name = "direccion")
    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;
    
    @Column(name = "telefono")
    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;

    public Persona(Long personaId, String nombre, String genero, Integer edad, String identificacion, String direccion,
            String telefono) {
        this.personaId = personaId;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Persona() {
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
