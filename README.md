# prueba_nttada
Desarrollo de prueba para semisenior

# Ejercicio Técnico Backend Java - Sistema Bancario
Descripción del Proyecto
Este proyecto implementa un sistema bancario dividido en dos microservicios que se comunican de forma asincrónica mediante RabbitMQ. Permite la gestión de clientes, cuentas y movimientos financieros.
Desarrollador: Andrés Prado Velarde
Nivel: SemiSenior
Arquitectura del Sistema: Microservicios

El sistema está separado en dos microservicios principales:

Microservicio Cliente-Persona

Gestión de clientes y personas
Endpoints RESTful para operaciones CRUD
Publicación de evento mediante RabbitMQ


Microservicio Cuenta-Movimiento

Gestión de cuentas bancarias
Registro de movimientos (depósitos y retiros)
Generación de reportes de estado de cuenta
Consumo de evento mediante RabbitMQ

Base de Datos

PostgreSQL como sistema de base de datos
Cada microservicio tiene su propia base de datos independiente

# Funcionalidades Implementadas

F1: CRUD completo para Cliente, Cuenta y Movimiento
F2: Registro de movimientos con actualización de saldo
F3: Validación de saldo con mensaje "Saldo no disponible"
F4: Reporte de estado de cuenta por rango de fechas y cliente
F5: Prueba unitaria para la entidad Cliente
F6: Prueba de integración para verificar la creación de clientes

# Tecnologías Utilizadas

Java 21
Spring Boot
Spring Data JPA
Spring AMQP (RabbitMQ)
PostgreSQL
JUnit 5 y Mockito para pruebas
Maven como gestor de dependencias
