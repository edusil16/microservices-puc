package br.com.boaentrega.modelo;

import br.com.boaentrega.dto.MercadoriaDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id_mercadoria")
    private Long id;
    
    @Column(name="descricao")
    private String produtoDescricao;
    
    @Column(name="tp_produto")
    private String tipoProduto;
    
    @Column(name="tp_unidade")
    private String tipoUnidade;
    
    @Column(name="quantidade")
    private String quantidade;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    @Column(name="acompanhemento_entrega")
    private String acompanhamentoEntrega;
    
    @Column(name="status_entrega")
    private String statusEntrega;
    
    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;
    
    @Column(name="fl_ativo")
    private Boolean ativo;
    
     public static Mercadoria create(MercadoriaDTO clienteDTO) {
        return new  ModelMapper().map(clienteDTO, Mercadoria.class);
    }
}
