/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.service;

import br.com.boaentrega.model.EntregaRealizada;
import br.com.boaentrega.model.Reembolso;
import br.com.boaentrega.repository.EntregaRealizadaRepository;
import br.com.boaentrega.repository.ReembolsoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo Santos
 */
@Service
public class GestaoService {
     private final EntregaRealizadaRepository entregaRealizadaRepository;
    private final ReembolsoRepository reembolsoRepository;
    
    @Autowired
    public GestaoService (EntregaRealizadaRepository entregaRealizadaRepository,ReembolsoRepository reembolsoRepository) {
        this.entregaRealizadaRepository = entregaRealizadaRepository;
        this.reembolsoRepository = reembolsoRepository;
    }
    
    public List<EntregaRealizada> listarEntregas() {
        return entregaRealizadaRepository.findAll();
    }
    
    public List<Reembolso> listarReembolsos() {
        return reembolsoRepository.findAll();
    }
}
