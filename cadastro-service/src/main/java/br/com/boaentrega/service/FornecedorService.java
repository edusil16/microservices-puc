/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.service;

import br.com.boaentrega.dto.NovoFornecedorDTO;
import br.com.boaentrega.message.FornecedorSendMessage;
import br.com.boaentrega.model.Fornecedor;
import br.com.boaentrega.repository.FornecedorRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo Santos
 */
@Service
public class FornecedorService {
    
    private final FornecedorRepository fornecedorRepository;
    private final FornecedorSendMessage fornecedorSendMessage;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository, FornecedorSendMessage fornecedorSendMessage) {
        this.fornecedorRepository = fornecedorRepository;
        this.fornecedorSendMessage = fornecedorSendMessage;
    }
    
    public Fornecedor inserirFornecedor(Fornecedor fornecedor) {
        var novoFornecedor = fornecedorRepository.save(fornecedor);
        fornecedorSendMessage.sendMessageFornecedor(NovoFornecedorDTO.create(fornecedor));
        return novoFornecedor;
    }

    public Fornecedor atualizarFornecedor(Fornecedor fornecedor) {
        var fornecedorCadastrado = fornecedorRepository.findById(fornecedor.getId());

        if (fornecedorCadastrado.isPresent()) {
            return fornecedorRepository.save(fornecedor);
        } else {
            return null;
        }
    }
    
    public boolean deletarFornecedor(Long id) {
        var fornecedorCadastrado = fornecedorRepository.findById(id);
        
        if(fornecedorCadastrado.isPresent()){
            fornecedorRepository.delete(fornecedorCadastrado.get());
            return true;
        } else {
            return false;
        }
    }
    
    public Optional<Fornecedor> buscarFornecedorPorId(Long id) {
        return fornecedorRepository.findById(id);
    }
    
}
