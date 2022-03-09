/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.boaentrega.model.dominio;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 *
 * @author Eduardo Santos
 */
@Getter
public enum AndamentoEntrega {
    
    SEPARACAO(1l),
    ANDAMENTO(2l),
    FINALIZADA(3l);
    
    private static final Map<Long, AndamentoEntrega> andamentoValor = new HashMap<>();

    private Long andamento;

    AndamentoEntrega(Long andamento) {
        this.andamento = andamento;
    }
    
    static {
        for (AndamentoEntrega andamento : AndamentoEntrega.values()) {
            andamentoValor.put(andamento.getAndamento(), andamento);
        }
    }
    
    public static AndamentoEntrega obterAndamentoPorValor(Long perfil) {
        return andamentoValor.get(perfil);
    }
}
