/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.modelo;

import br.com.boaentrega.dto.FornecedorDTO;
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
@Table(name = "tb_fornecedor_ativo")
public class Fornecedor {

    @Id
    @Column(name = "id_fonecedor")
    private Long id;
    @Column(name = "nm_fornecedor")
    private String nomeFornecedor;

    public static Fornecedor create(FornecedorDTO fornecedorDTO) {
        return new ModelMapper().map(fornecedorDTO, Fornecedor.class);
    }

}
