/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.controller;

import br.com.boaentrega.dto.ValorReembolsoDTO;
import br.com.boaentrega.model.EntregaRealizada;
import br.com.boaentrega.model.Reembolso;
import br.com.boaentrega.service.GestaoService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Eduardo Santos
 */
@RestController
@RequestMapping("/gestaoEntrega")
public class GestaoController {

    private final GestaoService gestaoService;

    public GestaoController(GestaoService gestaoService) {
        this.gestaoService = gestaoService;
    }

    @GetMapping("/entregas")
    public List<EntregaRealizada> buscarTodasEntregas() {
        return gestaoService.listarEntregas();
    }

    @GetMapping("/reembolsos")
    public List<Reembolso> buscarTodosReembolsos() {
        return gestaoService.listarReembolsos();
    }
    
    @GetMapping("/reembolsosPagar")
    public List<Reembolso> buscarTodosReembolsosAPagar() {
        return gestaoService.listarReembolsosAPagar();
    }
    
    @GetMapping("/reembolsosPagos")
    public List<Reembolso> buscarTodosReembolsosPagos() {
        return gestaoService.listarReembolsos();
    }
    
    @PostMapping("/gerarReembolso")
    public Reembolso gerarReembolso(@RequestBody ValorReembolsoDTO valor){
        var entrega = gestaoService.gerarReembolso(valor);
        return entrega;
    }
}
