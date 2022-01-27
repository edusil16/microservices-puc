/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.controlador;

import br.com.boaentrega.dto.AtualizaEntregaDTO;
import br.com.boaentrega.dto.NovaEntregaDTO;
import br.com.boaentrega.modelo.Entrega;
import br.com.boaentrega.servico.EntregaService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Eduardo Santos
 */
@RestController
@RequestMapping("/entrega")
public class EntregaController {

    private final EntregaService entregaService;

    @Autowired
    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }
    
    @PostMapping("/criarEntrega")
    public ResponseEntity inserirEntrega(@RequestBody NovaEntregaDTO entrega) {
        try {
            Date dataAtual = new Date();
            Entrega novaEntrega = new Entrega();

            novaEntrega.setIdCliente(entrega.getIdCliente());
            novaEntrega.setIdMercadoria(entrega.getIdMercadoria());
            novaEntrega.setCodDeposito(entrega.getCodDeposito());
            novaEntrega.setQuantidade(entrega.getQuantidade());
            novaEntrega.setDataSaida(dataAtual);
            novaEntrega.setStatus(entrega.getStatus());
               
            var retorno = entregaService.inserirNovaEntrega(novaEntrega);

            return ResponseEntity.ok(retorno);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    } 
    
    @PutMapping("/atualizarEntrega/{id}")
    public ResponseEntity atualizarFluxoEntrega(@PathVariable("id") Long id, @RequestBody AtualizaEntregaDTO entrega) {
        try {
            var entregaAtualzada = entregaService.atualizarAndamentoEntrega(id, entrega);
            return ResponseEntity.ok(entregaAtualzada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PostMapping("/acusarRecebimento/{id}")
    public ResponseEntity acusarRecebimento(@PathVariable("id") Long id) {
        try {
            entregaService.finalizarEntregar(id);
            return ResponseEntity.ok("Entrega finalizada!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PostMapping("/consultarEntrega/{id}")
    public ResponseEntity consultarEntrega(@PathVariable("id") Long id) {
        try {
            var retorno = entregaService.buscarEntregaPorId(id);
            return ResponseEntity.ok(retorno.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
