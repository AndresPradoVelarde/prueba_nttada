# prueba_nttada
Desarrollo de prueba para semisenior

# Ejercicio Técnico Backend Java - Sistema Bancario
Descripción del Proyecto<br>
Este proyecto implementa un sistema bancario dividido en dos microservicios que se comunican de forma asincrónica mediante  RabbitMQ. Permite la gestión de clientes, cuentas y movimientos financieros.<br>
Desarrollador: Andrés Prado Velarde<br>
Nivel: SemiSenior<br>
Arquitectura del Sistema: Microservicios<br>

El sistema está separado en dos microservicios principales:

Microservicio Cliente-Persona

Gestión de clientes y personas<br>
Endpoints RESTful para operaciones CRUD<br>
Publicación de evento mediante RabbitMQ<br>


Microservicio Cuenta-Movimiento

Gestión de cuentas bancarias<br>
Registro de movimientos (depósitos y retiros)<br>
Generación de reportes de estado de cuenta<br>
Consumo de evento mediante RabbitMQ<br>

Base de Datos

PostgreSQL como sistema de base de datos<br>
Cada microservicio tiene su propia base de datos independiente<br>

# Funcionalidades Implementadas

F1: CRUD completo para Cliente, Cuenta y Movimiento<br>
F2: Registro de movimientos con actualización de saldo<br>
F3: Validación de saldo con mensaje "Saldo no disponible"<br>
F4: Reporte de estado de cuenta por rango de fechas y cliente<br>
F5: Prueba unitaria para la entidad Cliente<br>
F6: Prueba de integración para verificar la creación de clientes<br>

# Tecnologías Utilizadas

Java 21<br>
Spring Boot<br>
Spring Data JPA<br>
Spring AMQP (RabbitMQ)<br>
PostgreSQL<br>
JUnit 5 y Mockito para pruebas<br>
Maven como gestor de dependencias<br>
