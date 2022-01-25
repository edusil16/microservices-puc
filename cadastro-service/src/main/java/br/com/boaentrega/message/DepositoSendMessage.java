/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

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
public class DepositoSendMessage {

    @Value("${cadastro.rabbitmq.exchange}")
    private String exchange;

    @Value("${cadastro.deposito.rabbitmq.routingkey}")
    private String routingKeyDeposito;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public DepositoSendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    public void sendMessageDeposito (NovoDepositoDTO deposito) {
        System.out.println(exchange);
        System.out.println(routingKeyDeposito);
        rabbitTemplate.convertAndSend(exchange, routingKeyDeposito, deposito);
    }
}
