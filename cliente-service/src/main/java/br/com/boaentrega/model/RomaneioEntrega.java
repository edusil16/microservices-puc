/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Eduardo Santos
 */

@Entity
@Table(name = "tb_romaneio")
@Data
public class RomaneioEntrega {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_romaneio")
    private Long id;

    @Column(name="id_entrega")
    private Long idEntrega;
    
    @Column(name="id_mercadoria")
    private Long idMercadoria;
    
    @Column(name="quantidade")
    private Integer quantidade;
}
