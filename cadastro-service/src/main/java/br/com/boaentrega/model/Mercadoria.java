package br.com.boaentrega.model;

import br.com.boaentrega.dto.MercadoriaDTO;
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
@Table(name="tb_mercadoria")
@Data
public class Mercadoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_mercadoria")
    private Long id;
    
    @Column(name="descricao")
    private String produtoDescricao;
    
    @Column(name="tp_produto")
    private String tipoProduto;
    
    @Column(name="tp_unidade")
    private String tipoUnidade;
    
    @Column(name="quatidade_estoque")
    private Integer qtdEstoque;

    @Column(name="fl_ativo")
    private Boolean ativo = true;
    
     public static Mercadoria create(MercadoriaDTO clienteDTO) {
        return new  ModelMapper().map(clienteDTO, Mercadoria.class);
    }
}
