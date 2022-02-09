/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.servico;

import br.com.boaentrega.dto.NovaMercadoriaDTO;
import br.com.boaentrega.message.MercadoriaSendMessage;
import br.com.boaentrega.modelo.Mercadoria;
import br.com.boaentrega.repositorio.MercadoriaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo Santos
 */
@Service
public class MercadoriaService {

    private final MercadoriaRepository mercadoriaRepository;
    private final MercadoriaSendMessage mercadoriaSendMessage;

    @Autowired
    public MercadoriaService(MercadoriaRepository mercadoriaRepository, MercadoriaSendMessage mercadoriaSendMessage) {
        this.mercadoriaRepository = mercadoriaRepository;
        this.mercadoriaSendMessage = mercadoriaSendMessage;
    }

    public Mercadoria inserirMercadoria(Mercadoria mercadoria) {
        var novaMercadoria = mercadoriaRepository.save(mercadoria);
        
        NovaMercadoriaDTO mercadoriaDTO = new NovaMercadoriaDTO();
        mercadoriaDTO.setId(novaMercadoria.getId());
        mercadoriaDTO.setProdutoDescricao(novaMercadoria.getProdutoDescricao());
        mercadoriaSendMessage.sendMessageMercadoria(mercadoriaDTO);
        
        return novaMercadoria;
    }

    public Mercadoria atualizarMercadoria(Mercadoria mercadoria) {
        var mercadoriaCadastrada = mercadoriaRepository.findById(mercadoria.getId());

        if (mercadoriaCadastrada.isPresent()) {
            return mercadoriaRepository.save(mercadoria);
        } else {
            return null;
        }
    }

    public boolean deletarMercadoria(Long id) {
        var mercadoriaCadastrada = mercadoriaRepository.findById(id);

        if (mercadoriaCadastrada.isPresent()) {
            mercadoriaRepository.delete(mercadoriaCadastrada.get());
            return true;
        } else {
            return false;
        }
    }

    public Optional<Mercadoria> buscarMercadoriaPorId(Long id) {
        return mercadoriaRepository.findById(id);
    }

}
