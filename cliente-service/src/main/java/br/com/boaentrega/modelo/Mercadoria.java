/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.modelo;

import br.com.boaentrega.dto.MercadoriaDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tb_mercadoria_fornecedor")
public class Mercadoria {

    @Id
    @Column(name = "id_mercadoria")
    private Long id;

    @Column(name = "id_fornecedor")
    private Long idFornecedor;

    @Column(name = "descricao")
    private String descricao;

    public static Mercadoria create(MercadoriaDTO mercadoriaDTO) {
        return new ModelMapper().map(mercadoriaDTO, Mercadoria.class);
    }
    
}
