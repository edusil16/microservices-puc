/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.service;

import br.com.boaentrega.dto.ClienteDTO;
import br.com.boaentrega.message.ClienteSendMessage;
import br.com.boaentrega.model.Cliente;
import br.com.boaentrega.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo Santos
 */
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteSendMessage clienteSendMessage;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteSendMessage clienteSendMessage) {
        this.clienteRepository = clienteRepository;
        this.clienteSendMessage = clienteSendMessage;
    }

    public Cliente inserirCliente(Cliente cliente) {
        var clienteNovo = clienteRepository.save(cliente);
        clienteSendMessage.sendMessageCliente(ClienteDTO.create(clienteNovo));
        return clienteNovo;
    }

    public Cliente atualizarCliente(Cliente cliente) {
        var clienteCadastrado = clienteRepository.findById(cliente.getId());

        if (clienteCadastrado.isPresent()) {
            return clienteRepository.save(cliente);
        } else {
            return null;
        }
    }

    public boolean deletarCliente(Long id) {
        var clienteCadastrado = clienteRepository.findById(id);

        if (clienteCadastrado.isPresent()) {
            clienteRepository.delete(clienteCadastrado.get());
            return true;
        } else {
            return false;
        }
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}
