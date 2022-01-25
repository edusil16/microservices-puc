/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

import br.com.boaentrega.dto.NovaMercadoriaDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Eduardo Santos
 */
@Component
public class MercadoriaSendMessage {

    @Value("${cadastro.rabbitmq.exchange}")
    private String exchange;
    
    @Value("${cadastro.mercadoria.rabbitmq.routingkey}")
    private String routingKeyMercadoria;
    
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MercadoriaSendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    public void sendMessageMercadoria (NovaMercadoriaDTO novaMercadoria) {
        System.out.println(exchange);
        System.out.println(routingKeyMercadoria);
        rabbitTemplate.convertAndSend(exchange, routingKeyMercadoria, novaMercadoria);
    }
}
