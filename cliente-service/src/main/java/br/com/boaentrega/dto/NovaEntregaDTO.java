/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.dto;

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
public class NovaEntregaDTO {
    private Long idDeposito;
    private Long idMercadoria;
    private Long idFornecedor;
    private Long idCliente;
    private String codDeposito;
    private String quantidade;
    private String status = "Em Separacao";
}
