/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.controlador;

import br.com.boaentrega.dto.DepositoDTO;
import br.com.boaentrega.modelo.Deposito;
import br.com.boaentrega.servico.DepositoService;
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
@RequestMapping("/deposito")
public class DepositoController {

    private final DepositoService depositoService;

    @Autowired
    public DepositoController(DepositoService depositoService) {
        this.depositoService = depositoService;
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity inserirDeposito(@RequestBody DepositoDTO deposito) {
        try {
            var depositoCadastrado = depositoService.inserirDeposito(Deposito.create(deposito));
            return ResponseEntity.ok(depositoCadastrado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizarDeposito(@PathVariable("id") Long id, @RequestBody DepositoDTO deposito) {
        try {
            var depositoCadastrado = Deposito.create(deposito);
            depositoCadastrado.setId(id);

            var depositoAtualizado = depositoService.atualizarDeposito(depositoCadastrado);

            if (depositoAtualizado != null) {
                return ResponseEntity.ok(depositoAtualizado);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deleteDeposito(@PathVariable("id") Long id) {
        try {
            return depositoService.deletarDeposito(id)
                    ? ResponseEntity.ok().build()
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscarDepositoPorId(@PathVariable("id") Long id) {
        try {
            var cliente = depositoService.buscarDepositoPorId(id);
            
            return cliente.isPresent() 
                    ? ResponseEntity.ok(cliente.get())
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }


}
