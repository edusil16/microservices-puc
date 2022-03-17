package br.com.boaentrega.model;

import br.com.boaentrega.dto.ClienteDTO;
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
@Table(name="tb_cliente")
@Data
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cliente")
    private Long id;
    
    @Column(name="nome")
    private String name;
    
    @Column(name="endereco")
    private String endereco;
    
    @Column(name="cpf")
    private String cpf;
    
    @Column(name="email")
    private String email;
    
    public static Cliente create(ClienteDTO clienteDTO) {
        return new  ModelMapper().map(clienteDTO, Cliente.class);
    }
}
