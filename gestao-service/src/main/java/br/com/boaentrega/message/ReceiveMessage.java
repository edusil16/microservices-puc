/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

import br.com.boaentrega.dto.RomaneioEntregaDTO;
import br.com.boaentrega.model.EntregaRealizada;
import br.com.boaentrega.model.Reembolso;
import br.com.boaentrega.repository.EntregaRealizadaRepository;
import br.com.boaentrega.repository.ReembolsoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiveMessage {
    
    private final EntregaRealizadaRepository entregaRealizadaRepository;
    private final ReembolsoRepository reembolsoRepository;
    
    @Autowired
    public ReceiveMessage (EntregaRealizadaRepository entregaRealizadaRepository,ReembolsoRepository reembolsoRepository) {
        this.entregaRealizadaRepository = entregaRealizadaRepository;
        this.reembolsoRepository = reembolsoRepository;
    }
    
     @RabbitListener(queues = {"${cliente.entrega.rabbitmq.queue}"})
     public void processarEntrega(RomaneioEntregaDTO romaneio){
        var operacao = romaneio.getDescricao();
        
        if (operacao.equals("sucesso")) {
            EntregaRealizada entrega = new EntregaRealizada();
            entrega.setNumEntrega(romaneio.getNumEntrega());
            entregaRealizadaRepository.save(entrega);
        } else {
            Reembolso reembolso = new Reembolso();
            reembolso.setNumEntrega(romaneio.getNumEntrega());
            reembolso.setMotivo(romaneio.getDescricao());
            reembolsoRepository.save(reembolso);
        }
        
     }
}
