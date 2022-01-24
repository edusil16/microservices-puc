/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.servico;

import br.com.boaentrega.modelo.Fornecedor;
import br.com.boaentrega.repositorio.FornecedorRepository;
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

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }
    
    public Fornecedor inserirFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
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
