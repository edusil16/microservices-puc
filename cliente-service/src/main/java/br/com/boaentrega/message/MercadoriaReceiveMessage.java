/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

import br.com.boaentrega.dto.MercadoriaDTO;
import br.com.boaentrega.model.Mercadoria;
import br.com.boaentrega.repository.MercadoriaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 * @author Eduardo Santos
 */
@Component
public class MercadoriaReceiveMessage {
    
    private final MercadoriaRepository mercadoriaRepository;

    @Autowired
    public MercadoriaReceiveMessage(MercadoriaRepository mercadoriaRepository) {
        this.mercadoriaRepository = mercadoriaRepository;
    }  
    
    @RabbitListener(queues = {"${cadastro.mercadoria.rabbitmq.queue}"})
    public void receiveMessageDeposito(@Payload MercadoriaDTO mercadoria) {
        System.out.println(mercadoria);
        mercadoriaRepository.save(Mercadoria.create(mercadoria));
    }
    
}
