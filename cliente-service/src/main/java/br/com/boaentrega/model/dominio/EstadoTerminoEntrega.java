/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.boaentrega.model.dominio;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public enum EstadoTerminoEntrega {
    
    EXTRAVIADO(1l),
    SUCESSO(2l);
    
    private static final Map<Long, EstadoTerminoEntrega> entregaTerminoValor = new HashMap<>();
    
    private Long entregaTermino;
    
    EstadoTerminoEntrega(Long entregaTermino) {
        this.entregaTermino = entregaTermino;
    }
    
    static {
        for (EstadoTerminoEntrega valorTermino : EstadoTerminoEntrega.values()) {
            entregaTerminoValor.put(valorTermino.getEntregaTermino(), valorTermino);
        }
    }
    
    public static EstadoTerminoEntrega obterTerminoPorValor(Long valorFinal) {
        return entregaTerminoValor.get(valorFinal);
    }
}
