/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.modelo;

import br.com.boaentrega.dto.NovaEntregaDTO;
import java.util.Date;
import javax.persistence.Column;
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
    @Column(name = "id_entrega")
    private Long idEntrega;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "id_mercadoria")
    private Long idMercadoria;

    @Column(name = "cod_deposito")
    private String codDeposito;

    @Column(name = "quantidade")
    private String quantidade;

    @Column(name = "dt_saida")
    private Date dataSaida;

    @Column(name = "dt_atualizacao")
    private Date dataAtualizacao;

    @Column(name = "dt_entrega")
    private Date dataEntrega;

    @Column(name = "status")
    private String status;

    public static Entrega create(NovaEntregaDTO entregaDTO) {
        return new ModelMapper().map(entregaDTO, Entrega.class);
    }
}
