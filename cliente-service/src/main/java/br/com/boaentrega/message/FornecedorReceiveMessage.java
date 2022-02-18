/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

import br.com.boaentrega.dto.FornecedorDTO;
import br.com.boaentrega.model.Fornecedor;
import br.com.boaentrega.repository.FornecedorRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 * @author Eduardo Santos
 */
@Component
public class FornecedorReceiveMessage {

    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorReceiveMessage(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @RabbitListener(queues = {"${cadastro.fornecedor.rabbitmq.queue}"})
    public void receiveMessageFornecedor(@Payload FornecedorDTO fornecedor) {
        System.out.println(fornecedor);
        fornecedorRepository.save(Fornecedor.create(fornecedor));
    }
}
