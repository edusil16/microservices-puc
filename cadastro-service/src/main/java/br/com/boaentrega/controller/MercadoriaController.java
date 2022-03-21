/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.controller;

import br.com.boaentrega.dto.MercadoriaDTO;
import br.com.boaentrega.model.Mercadoria;
import br.com.boaentrega.service.FornecedorService;
import br.com.boaentrega.service.MercadoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/mercadoria")
public class MercadoriaController {

    private final MercadoriaService mercadoriaService;


    @Autowired
    public MercadoriaController(MercadoriaService mercadoriaService) {
        this.mercadoriaService = mercadoriaService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity inserirMercadoria(@RequestBody MercadoriaDTO mercadoria) {
        try {
            mercadoriaService.inserirMercadoria(Mercadoria.create(mercadoria));
            return ResponseEntity.ok(mercadoria);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizarMercadoria(@PathVariable("id") Long id, @RequestBody MercadoriaDTO mercadoria) {
        try {
            var mercadoriaCadastrada = Mercadoria.create(mercadoria);
            mercadoriaCadastrada.setId(id);

            var mercadoriaAtualizada = mercadoriaService.atualizarMercadoria(mercadoriaCadastrada);

            if (mercadoriaAtualizada != null) {
                return ResponseEntity.ok(mercadoriaAtualizada);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deleteMercadoria(@PathVariable("id") Long id) {
        try {
            return mercadoriaService.deletarMercadoria(id)
                    ? ResponseEntity.ok().build()
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscarMercadoriaPorId(@PathVariable("id") Long id) {
        try {
            var cliente = mercadoriaService.buscarMercadoriaPorId(id);

            return cliente.isPresent()
                    ? ResponseEntity.ok(cliente.get())
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @GetMapping("/buscarTodos")
    public List<Mercadoria> buscarListaDeposito() {
        return mercadoriaService.listarMercadorias();
    }
}
