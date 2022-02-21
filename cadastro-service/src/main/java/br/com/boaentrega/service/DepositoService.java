/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.service;

import br.com.boaentrega.dto.NovoDepositoDTO;
import br.com.boaentrega.message.DepositoSendMessage;
import br.com.boaentrega.model.Deposito;
import br.com.boaentrega.repository.DepositoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo Santos
 */
@Service
public class DepositoService {
    
    private final DepositoRepository depositoRepository;
    private final DepositoSendMessage depositoSendMessage;

    @Autowired
    public DepositoService(DepositoRepository depositoRepository, DepositoSendMessage depositoSendMessage) {
        this.depositoRepository = depositoRepository;
        this.depositoSendMessage = depositoSendMessage;
    }
    
    public Deposito inserirDeposito(Deposito deposito) {
        var depositoNovo = depositoRepository.save(deposito);
        depositoSendMessage.sendMessageDeposito(NovoDepositoDTO.create(depositoNovo));
        return depositoNovo;
    }

    public Deposito atualizarDeposito(Deposito deposito) {
        var mercadoriaCadastrada = depositoRepository.findById(deposito.getId());

        if (mercadoriaCadastrada.isPresent()) {
            return depositoRepository.save(deposito);
        } else {
            return null;
        }
    }
    
    public boolean deletarDeposito(Long id) {
        var mercadoriaCadastrada = depositoRepository.findById(id);
        
        if(mercadoriaCadastrada.isPresent()){
            depositoRepository.delete(mercadoriaCadastrada.get());
            return true;
        } else {
            return false;
        }
    }
    
    public Optional<Deposito> buscarDepositoPorId(Long id) {
        return depositoRepository.findById(id);
    }
    
    public List<Deposito> buscarTodos() {
        return depositoRepository.findAll();
    }
    
}
