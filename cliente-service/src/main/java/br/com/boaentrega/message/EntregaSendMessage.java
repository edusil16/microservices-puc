/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

import br.com.boaentrega.dto.BaixaEstoqueDTO;
import br.com.boaentrega.dto.RomaneioEntregaDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Eduardo Santos
 */
@Component
public class EntregaSendMessage {
    
    @Value("${cadastro.rabbitmq.exchange}")
    private String exchange;
    
    @Value("${gestao.cliente.rabbitmq.routingkey}")
    private String routingKeyCliente;
    
    @Value("${gestao.estoque.rabbitmq.routingkey}")
    private String routingKeyBaixaEstoque;
    
    private final RabbitTemplate rabbitTemplate;
    
    @Autowired
    public EntregaSendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    public void sendMessageCliente (RomaneioEntregaDTO romaneio) {
        System.out.println(exchange);
        System.out.println(routingKeyCliente);
        rabbitTemplate.convertAndSend(exchange, routingKeyCliente, romaneio);
    }
    
    public void sendMessageBaixaEstoque (BaixaEstoqueDTO baixaEstoque) {
        System.out.println(exchange);
        System.out.println(routingKeyCliente);
        rabbitTemplate.convertAndSend(exchange, routingKeyBaixaEstoque, baixaEstoque);
    }
}
