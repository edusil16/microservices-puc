/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.controller;

import br.com.boaentrega.dto.FornecedorDTO;
import br.com.boaentrega.model.Fornecedor;
import br.com.boaentrega.service.FornecedorService;
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
@RequestMapping("/fornecedor")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity inserirFornecedor(@RequestBody FornecedorDTO fornecedor) {
        try {
            var fornecedorCadastrado = fornecedorService.inserirFornecedor(Fornecedor.create(fornecedor));
            return ResponseEntity.ok(fornecedorCadastrado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizarFornecedor(@PathVariable("id") Long id, @RequestBody FornecedorDTO fornecedor) {
        try {
            var fornecedorCadastrado = Fornecedor.create(fornecedor);
            fornecedorCadastrado.setId(id);

            var clienteAtualizado = fornecedorService.atualizarFornecedor(fornecedorCadastrado);

            if (clienteAtualizado != null) {
                return ResponseEntity.ok(clienteAtualizado);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deleteFornecedor(@PathVariable("id") Long id) {
        try {
            return fornecedorService.deletarFornecedor(id)
                    ? ResponseEntity.ok().build()
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscarFornecedorPorId(@PathVariable("id") Long id) {
        try {
            var cliente = fornecedorService.buscarFornecedorPorId(id);
            
            return cliente.isPresent() 
                    ? ResponseEntity.ok(cliente.get())
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

}
