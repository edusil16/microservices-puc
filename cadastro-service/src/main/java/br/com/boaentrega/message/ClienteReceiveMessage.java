/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

import br.com.boaentrega.dto.BaixaEstoqueDTO;
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
public class ClienteReceiveMessage {

    private final MercadoriaRepository mercadoriaRepository;

    @Autowired
    public ClienteReceiveMessage(MercadoriaRepository mercadoriaRepository) {
        this.mercadoriaRepository = mercadoriaRepository;
    }

    @RabbitListener(queues = {"${gestao.estoque.rabbitmq.queue}"})
    public void receiveMessageBaixaEstoque(@Payload BaixaEstoqueDTO baixaEstoque) {
        System.out.println();
        var mercadoria = mercadoriaRepository.findById(baixaEstoque.getIdMercadoria());
        
        Integer estoqueAtual = mercadoria.get().getQtdEstoque();
        var estoqueFinal = estoqueAtual - baixaEstoque.getQuantidade();
        
        mercadoria.get().setQtdEstoque(estoqueFinal);
        mercadoriaRepository.save(mercadoria.get());
    }

}
