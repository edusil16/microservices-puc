/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.model;

import br.com.boaentrega.dto.DepositoDTO;
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
@Table(name = "tb_deposito")
public class Deposito {

    @Id
    @Column(name = "id_deposito")
    private Long id;
    @Column(name = "cod_deposito")
    private String codDeposito;

    public static Deposito create(DepositoDTO depositoDTO) {
        return new ModelMapper().map(depositoDTO, Deposito.class);
    }

}
