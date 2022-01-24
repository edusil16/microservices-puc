package br.com.boaentrega.controlador;

import br.com.boaentrega.dto.ClienteDTO;
import br.com.boaentrega.modelo.Cliente;
import br.com.boaentrega.servico.ClienteService;
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
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity inserirCliente(@RequestBody ClienteDTO cliente) {
        try {
            var clienteCadastrado = clienteService.inserirCliente(Cliente.create(cliente));
            return ResponseEntity.ok(clienteCadastrado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizarCliente(@PathVariable("id") Long id, @RequestBody ClienteDTO cliente) {
        try {
            var clienteCadastrado = Cliente.create(cliente);
            clienteCadastrado.setId(id);

            var clienteAtualizado = clienteService.atualizarCliente(clienteCadastrado);

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
    public ResponseEntity deleteCliente(@PathVariable("id") Long id) {
        try {
            return clienteService.deletarCliente(id)
                    ? ResponseEntity.ok().build()
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscarClientePorId(@PathVariable("id") Long id) {
        try {
            var cliente = clienteService.buscarClientePorId(id);
            
            return cliente.isPresent() 
                    ? ResponseEntity.ok(cliente.get())
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

}
