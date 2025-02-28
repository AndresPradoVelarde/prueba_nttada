package com.prueba.nttdata.cuenta_movimiento.messaging.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prueba.nttdata.cuenta_movimiento.dtos.ClienteMensajeDTO;
import com.prueba.nttdata.cuenta_movimiento.models.Cuenta;
import com.prueba.nttdata.cuenta_movimiento.services.CuentaService;

@Component
public class ClienteEventListener {
    private static final Logger logger = LoggerFactory.getLogger(ClienteEventListener.class);

    @Autowired
    private CuentaService cuentaService;

    @RabbitListener(queues = "${rabbitmq.queue.cliente}")
    public void procesarEventoCliente(ClienteMensajeDTO mensaje) {
        logger.info("Recibido evento de cliente creado: {}", mensaje.getClienteId());

        try {
            Cuenta nuevaCuenta = new Cuenta();
            long numeroCuenta = (long)(Math.random() * 9000000000L) + 1000000000L;
            nuevaCuenta.setNumeroCuenta(String.valueOf(numeroCuenta));
            nuevaCuenta.setTipoCuenta("Ahorro");
            nuevaCuenta.setSaldoInicial(0.0);
            nuevaCuenta.setEstado(true);
            nuevaCuenta.setClienteId(mensaje.getClienteId());
            nuevaCuenta.setNombreCliente(mensaje.getNombre());

            cuentaService.save(nuevaCuenta);

            logger.info("Cuenta de ahorro creada automáticamente para el cliente {}: {}",
                    mensaje.getClienteId(), nuevaCuenta.getNumeroCuenta());

        } catch (Exception e) {
            logger.error("Error al crear cuenta para cliente: {}", e.getMessage(), e);
        }
    }

}
