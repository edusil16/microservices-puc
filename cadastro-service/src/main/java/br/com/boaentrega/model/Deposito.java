package br.com.boaentrega.model;

import br.com.boaentrega.dto.DepositoDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Eduardo Santos
 */
@Entity
@Table(name="tb_deposito")
@Data
public class Deposito {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id_deposito")
    private Long id;
    
    @Column(name="regiao")
    private String regiao;
    
    @Column(name="endereco")
    private String endereco;
    
    @Column(name="cod_deposito")
    private String codDeposito;
    
    @Column(name="fl_ativo")
    private Boolean ativo;
    
     public static Deposito create(DepositoDTO clienteDTO) {
        return new  ModelMapper().map(clienteDTO, Deposito.class);
    }
}
