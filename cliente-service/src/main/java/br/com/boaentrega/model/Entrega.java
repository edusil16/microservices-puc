/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.model;

import br.com.boaentrega.dto.NovaEntregaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;
    
    @Column(name = "num_entrega")
    private String numeroEntrega;
    
    @Column(name = "cod_primeiro_deposito")
    private String codPrimeiroDeposito;
    
    @Column(name = "cod_ultimo_deposito")
    private String codUltimoDeposito;
    
    @Column(name = "volume")
    private Integer volume;

    @Column(name = "dt_criacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dataCriacao;

    @Column(name = "dt_atualizacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dataAtualizacao;

    @Column(name = "dt_entrega")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dataEntrega;

    @Column(name = "status")
    private String status;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_romaneio_entrega",
            joinColumns = @JoinColumn(name = "id_entrega"),
            inverseJoinColumns = @JoinColumn(name = "id_mercadoria"))
    private Set<Mercadoria> mercadorias;

    public static Entrega create(NovaEntregaDTO entregaDTO) {
        return new ModelMapper().map(entregaDTO, Entrega.class);
    }
}
