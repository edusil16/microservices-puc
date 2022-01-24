package br.com.boaentrega.modelo;

import br.com.boaentrega.dto.ClienteDTO;
import com.sun.istack.NotNull;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id_cliente")
    private Long id;
    
    @Column(name="nome")
    private String name;
    
    @Column(name="cpf")
    private String cpf;
    
    @Column(name="email")
    private String email;
    
    @OneToMany(mappedBy="cliente")
    private List<Mercadoria> mercadoriaEntraga;
    
    @OneToOne
    @NotNull
    private Usuario usuario;
    
    public static Cliente create(ClienteDTO clienteDTO) {
        return new  ModelMapper().map(clienteDTO, Cliente.class);
    }
}
