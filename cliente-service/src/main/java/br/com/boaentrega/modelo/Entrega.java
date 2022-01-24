/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Eduardo Santos
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_entregas")
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idEntrega;
    private Long idCliente;
    private Long idMercadoria;
    private Long idDeposito;
    
//    public static Entrega create(EntregaDTO entregaDTO) {
//        return new ModelMapper().map(entregaDTO, Entrega.class);
//    }
}
