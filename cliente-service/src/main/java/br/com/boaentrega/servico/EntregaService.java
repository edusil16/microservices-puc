/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.servico;

import br.com.boaentrega.modelo.Entrega;
import br.com.boaentrega.repositorio.EntregaRepository;
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
    
    public void atualizarAndamentoEntrega(Long id) {
        
    }
    
    public void finalizarEntregar(Long id) {
        
    }
    
    public void buscarEntregaPorId(Long id) {
        
    }

}
