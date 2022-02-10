/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.dto;

import br.com.boaentrega.modelo.Mercadoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Eduardo Santos
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NovaMercadoriaDTO {

    private Long id;
    private String produtoDescricao;

    public static NovaMercadoriaDTO create(Mercadoria mercadoria) {
        return new ModelMapper().map(mercadoria, NovaMercadoriaDTO.class);
    }
}
