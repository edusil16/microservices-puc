/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Eduardo Santos
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MercadoriaDTO {  
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private Long quantidade;
    private String numEntrega;
    private String produtoDescricao;
}
