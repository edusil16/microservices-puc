/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

import br.com.boaentrega.dto.ClienteDTO;
import br.com.boaentrega.model.Cliente;
import br.com.boaentrega.repository.ClienteRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 * @author Eduardo Santos
 */
@Component
public class ClienteReceiveMessage {
    
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteReceiveMessage(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }  
    
    @RabbitListener(queues = {"${cadastro.cliente.rabbitmq.queue}"})
    public void receiveMessageDeposito(@Payload ClienteDTO cliente) {
        System.out.println(cliente);
        clienteRepository.save(Cliente.create(cliente));
    }
}
