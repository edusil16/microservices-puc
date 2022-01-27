/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.servico;

import br.com.boaentrega.dto.AtualizaEntregaDTO;
import br.com.boaentrega.modelo.Entrega;
import br.com.boaentrega.repositorio.EntregaRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo Santos
 */
@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    @Autowired
    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public Entrega inserirNovaEntrega(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public Entrega atualizarAndamentoEntrega(Long id, AtualizaEntregaDTO entrega) {
        Date date = new Date();
        var entregaAtual = entregaRepository.findById(id);
        entregaAtual.get().setCodDeposito(entrega.getCodDeposito());
        entregaAtual.get().setStatus(entrega.getStatus());
        entregaAtual.get().setDataAtualizacao(date);
        var entregaAtualizada = entregaRepository.save(entregaAtual.get());
        return entregaAtualizada;
    }

    public void finalizarEntregar(Long id) {
        Date date = new Date();
        var entrega = entregaRepository.findById(id);
        entrega.get().setDataEntrega(date);
        entrega.get().setStatus("Entregue ao Cliente");
        entregaRepository.save(entrega.get());
    }

    public Optional<Entrega> buscarEntregaPorId(Long id) {
        return entregaRepository.findById(id);
    }

}
