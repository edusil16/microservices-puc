/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.model;

import br.com.boaentrega.dto.ClienteDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Eduardo Santos
 */
@Entity
@Table(name = "tb_cliente")
@Data
public class Cliente {

    @Id
    @Column(name = "id_cliente")
    private Long id;
    @Column(name = "nome_cliente")
    private String name;
    @Column(name = "endereco")
    private String endereco;

    public static Cliente create(ClienteDTO clienteDTO) {
        return new ModelMapper().map(clienteDTO, Cliente.class);
    }

}
