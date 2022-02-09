/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

import br.com.boaentrega.dto.UsuarioDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Eduardo Santos
 */
@Component
public class UsuarioSendMessage {

    @Value("${cadastro.rabbitmq.exchange}")
    private String exchange;

    @Value("${cadastro.usuario.rabbitmq.routingkey}")
    private String routingKeyMercadoria;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UsuarioSendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageCliente(UsuarioDTO novaMercadoria) {
        System.out.println(exchange);
        System.out.println(routingKeyMercadoria);
        rabbitTemplate.convertAndSend(exchange, routingKeyMercadoria, novaMercadoria);
    }
}
