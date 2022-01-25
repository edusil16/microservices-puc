/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

import br.com.boaentrega.dto.NovoFornecedorDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Eduardo Santos
 */
@Component
public class FornecedorSendMessage {

    @Value("${cadastro.rabbitmq.exchange}")
    private String exchange;
    
    @Value("${cadastro.fornecedor.rabbitmq.routingkey}")
    private String routingKeyFornecedor;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public FornecedorSendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    public void sendMessageFornecedor (NovoFornecedorDTO fornecedor) {
        System.out.println(exchange);
        System.out.println(routingKeyFornecedor);
        rabbitTemplate.convertAndSend(exchange, routingKeyFornecedor, fornecedor);
    }
}
