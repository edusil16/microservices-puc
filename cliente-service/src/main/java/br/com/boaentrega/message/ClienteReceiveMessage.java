/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

import br.com.boaentrega.dto.DepositoDTO;
import br.com.boaentrega.modelo.Deposito;
import br.com.boaentrega.repositorio.DepositoRepository;
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
    
    private final DepositoRepository depositoRepository;

    @Autowired
    public ClienteReceiveMessage(DepositoRepository depositoRepository) {
        this.depositoRepository = depositoRepository;
    }  
    
    @RabbitListener(queues = {"${cadastro.deposito.rabbitmq.queue}"})
    public void receiveMessageDeposito(@Payload DepositoDTO deposito) {
        System.out.println(deposito);
        depositoRepository.save(Deposito.create(deposito));
    }
}
