/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.controller;

import br.com.boaentrega.dto.AtualizaEntregaDTO;
import br.com.boaentrega.dto.MercadoriaDTO;
import br.com.boaentrega.dto.NovaEntregaDTO;
import br.com.boaentrega.model.Cliente;
import br.com.boaentrega.model.Deposito;
import br.com.boaentrega.model.Entrega;
import br.com.boaentrega.model.Fornecedor;
import br.com.boaentrega.model.RomaneioEntrega;
import br.com.boaentrega.model.dominio.AndamentoEntrega;
import br.com.boaentrega.service.EntregaService;
import java.util.Date;
import java.util.Optional;
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
            
            /* Recuperando os valores de Cliente, Depósito e Fornecedor */
            Optional<Fornecedor> fornecedor = entregaService.buscarFornecedorPorId(entrega.getIdFornecedor());
            Optional<Deposito> deposito = entregaService.buscarDepositoPorId(entrega.getIdDeposito());
            Optional<Cliente> cliente = entregaService.buscarClientePorId(entrega.getIdCliente());
            
            Date dataAtual = new Date();
            Entrega novaEntrega = new Entrega();
 
            AndamentoEntrega andamento = AndamentoEntrega.obterPerfilPorValor(entrega.getStatus());
            
            novaEntrega.setCliente(cliente.get());
            novaEntrega.setFornecedor(fornecedor.get());
            novaEntrega.setCodPrimeiroDeposito(deposito.get().getCodDeposito());
            novaEntrega.setNumeroEntrega(entregaService.gerarNumeroEntrega());
            novaEntrega.setCodPrimeiroDeposito(deposito.get().getCodDeposito());
            novaEntrega.setVolume(entrega.getMercadorias().size());
            novaEntrega.setDataCriacao(dataAtual);
            novaEntrega.setStatus(andamento.toString());
               
            var retorno = entregaService.inserirNovaEntrega(novaEntrega);
            
            for (MercadoriaDTO idMercadoria : entrega.getMercadorias()){   
                RomaneioEntrega romaneio = new RomaneioEntrega();
                var mercadoria = entregaService.buscarMercadoriaPorId(idMercadoria.getId());
                romaneio.setIdEntrega(retorno.getIdEntrega());
                romaneio.setIdMercadoria(mercadoria.get().getId());
                entregaService.inserirMercadoriaRomaneio(romaneio);
            }
                       
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
            return ResponseEntity.ok(retorno);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
