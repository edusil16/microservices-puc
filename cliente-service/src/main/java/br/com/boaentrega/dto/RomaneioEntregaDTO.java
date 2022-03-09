/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.dto;

import java.util.Map;
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
public class RomaneioEntregaDTO {
    private String codDeposito;
    private String dtCriacao;
    private String numEntrega;
    private String unidades;
    private String descricao;
    private Map<String, Object> rotas;
}
