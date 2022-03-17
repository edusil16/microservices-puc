/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.controller;

import br.com.boaentrega.dto.AtualizaEntregaDTO;
import br.com.boaentrega.dto.BaixaEstoqueDTO;
import br.com.boaentrega.dto.ClienteDTO;
import br.com.boaentrega.dto.DirectionsDTO;
import br.com.boaentrega.dto.MercadoriaDTO;
import br.com.boaentrega.dto.MercadoriaPedidoDTO;
import br.com.boaentrega.dto.NovaEntregaDTO;
import br.com.boaentrega.model.Cliente;
import br.com.boaentrega.model.Deposito;
import br.com.boaentrega.model.Entrega;
import br.com.boaentrega.model.Fornecedor;
import br.com.boaentrega.model.RomaneioEntrega;
import br.com.boaentrega.model.dominio.AndamentoEntrega;
import br.com.boaentrega.service.EntregaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

            var rota = entregaService.calcularRota(deposito.get().getEndereco().replace(" ", "+"), cliente.get().getEndereco().replace(" ", "+"));

            Date dataAtual = new Date();
            Entrega novaEntrega = new Entrega();

            AndamentoEntrega andamento = AndamentoEntrega.obterAndamentoPorValor(entrega.getStatus());

            novaEntrega.setCliente(cliente.get());
            novaEntrega.setFornecedor(fornecedor.get());
            novaEntrega.setCodPrimeiroDeposito(deposito.get().getCodDeposito());
            novaEntrega.setNumeroEntrega(entregaService.gerarNumeroEntrega());
            novaEntrega.setCodPrimeiroDeposito(deposito.get().getCodDeposito());
            novaEntrega.setVolume(entrega.getMercadorias().size());
            novaEntrega.setDataCriacao(dataAtual);
            novaEntrega.setStatus(andamento.toString());

            var entregaCriada = entregaService.inserirNovaEntrega(novaEntrega);

            for (MercadoriaPedidoDTO mercadoriaPedida : entrega.getMercadorias()) {
                RomaneioEntrega romaneio = new RomaneioEntrega();
                var mercadoria = entregaService.buscarMercadoriaPorId(mercadoriaPedida.getId());
                romaneio.setIdEntrega(entregaCriada.getIdEntrega());
                romaneio.setIdMercadoria(mercadoria.get().getId());
                romaneio.setQuantidade(mercadoriaPedida.getQtdPedida());
                entregaService.inserirMercadoriaRomaneio(romaneio);

                /* Baixa no estoque */
                BaixaEstoqueDTO baixaEstoque = new BaixaEstoqueDTO();
                baixaEstoque.setIdMercadoria(mercadoriaPedida.getId());
                baixaEstoque.setQuantidade(mercadoriaPedida.getQtdPedida());
                entregaService.enviarBaixaEstoque(baixaEstoque);
            }

            Gson gson = new Gson();
            
            var json = gson.toJson(rota.body());
            
            var entregaGerada = new HashMap<String, Object>();
            entregaGerada.put("rota", json);
            entregaGerada.put("entrega", entregaCriada);
            return ResponseEntity.ok(entregaGerada);

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
            var romaneio = entregaService.buscarRomaneioPorId(id);

            ClienteDTO clienteEntrega = new ClienteDTO();
            clienteEntrega.setName(romaneio.get(0).getCliente());
            clienteEntrega.setEndereco(romaneio.get(0).getEndereco());

            List<MercadoriaDTO> mercadoriaEntrega = new ArrayList<>();

            for (var mercadoria : romaneio) {
                MercadoriaDTO mercadoriaRomaneio = new MercadoriaDTO();
                var descricao = mercadoria.getDescricao();
                var quantidade = mercadoria.getQuantidade();
                var numEntrega = mercadoria.getNumEntrega();

                mercadoriaRomaneio.setProdutoDescricao(descricao);
                mercadoriaRomaneio.setQuantidade(quantidade);
                mercadoriaRomaneio.setNumEntrega(numEntrega);

                mercadoriaEntrega.add(mercadoriaRomaneio);
            }

            HashMap<String, ClienteDTO> clienteMapa = new HashMap<>();
            HashMap<String, List<MercadoriaDTO>> mercadoriaMapa = new HashMap<>();

            clienteMapa.put("Dados Cliente", clienteEntrega);
            mercadoriaMapa.put("Dados Mercadoria", mercadoriaEntrega);

            HashMap<Object, Object> mapaEntrega = new HashMap<>();
            mapaEntrega.put("Entrega", clienteMapa);
            mapaEntrega.put("Mercadorias", mercadoriaMapa);

            return ResponseEntity.ok(mapaEntrega);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
