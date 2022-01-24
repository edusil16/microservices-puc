/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.servico;

import br.com.boaentrega.dto.NovoDepositoDTO;
import br.com.boaentrega.message.ClienteSendMessage;
import br.com.boaentrega.modelo.Deposito;
import br.com.boaentrega.repositorio.DepositoRepository;
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
    private final ClienteSendMessage clienteSendMessage;

    @Autowired
    public DepositoService(DepositoRepository depositoRepository, ClienteSendMessage clienteSendMessage) {
        this.depositoRepository = depositoRepository;
        this.clienteSendMessage = clienteSendMessage;
    }
    
    public Deposito inserirDeposito(Deposito deposito) {
        var depositoNovo = depositoRepository.save(deposito);
        clienteSendMessage.sendMessageDeposito(NovoDepositoDTO.create(depositoNovo));
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
    
}
