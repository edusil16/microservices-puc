/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

import br.com.boaentrega.dto.ClienteDTO;
import br.com.boaentrega.dto.NovoDepositoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Eduardo Santos
 */
@Component
public class ClienteSendMessage {
    
    @Value("${cadastro.rabbitmq.exchange}")
    private String exchange;
    
    @Value("${cadastro.cliente.rabbitmq.routingkey}")
    private String routingKeyCliente;
    
    private final RabbitTemplate rabbitTemplate;
    
    @Autowired
    public ClienteSendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    public void sendMessageCliente (ClienteDTO clienteDTO) {
        System.out.println(exchange);
        System.out.println(routingKeyCliente);
        rabbitTemplate.convertAndSend(exchange, routingKeyCliente, clienteDTO);
    }
}
