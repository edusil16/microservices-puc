/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.boaentrega.model.dominio;

/**
 *
 * @author Eduardo Santos
 */

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public enum DescricaoFinalEntrega {
    
    ENVIADA(1l),
    ROUBADA(2l),
    AVARIADA(3l);
    
    private static final Map<Long, DescricaoFinalEntrega> descricaoValor = new HashMap<>();

    private Long descricao;

    DescricaoFinalEntrega(Long descricao) {
        this.descricao = descricao;
    }
    
    static {
        for (DescricaoFinalEntrega descricaoFinal : DescricaoFinalEntrega.values()) {
            descricaoValor.put(descricaoFinal.getDescricao(), descricaoFinal);
        }
    }
    
    public static DescricaoFinalEntrega obterAndamentoPorValor(Long valorDescricao) {
        return descricaoValor.get(valorDescricao);
    }
}
