package com.prueba.nttdata.cliente.messaging.publisher;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.nttdata.cliente.dtos.ClienteDTO;
import com.prueba.nttdata.cliente.dtos.ClienteMensajeDTO;
import com.prueba.nttdata.cliente.models.Cliente;

@Service
public class ClienteEventPublisher {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private Queue queue;
    
    public void publishClienteCreated(ClienteDTO clienteDTO) {
        rabbitTemplate.convertAndSend(queue.getName(), clienteDTO);
    }

    public void publishClienteCreado(Cliente cliente) {
        ClienteMensajeDTO mensaje = new ClienteMensajeDTO(cliente.getClienteId(),cliente.getPersona().getNombre());
        rabbitTemplate.convertAndSend(queue.getName(), mensaje);
    }
    
}
