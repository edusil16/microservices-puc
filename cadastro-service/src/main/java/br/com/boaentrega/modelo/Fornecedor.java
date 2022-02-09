package br.com.boaentrega.modelo;

import br.com.boaentrega.dto.FornecedorDTO;
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
@Table(name = "tb_fornecedor")
@Data
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_fornecedor")
    private Long id;

    @Column(name = "nm_fornecedor")
    private String nomeFornecedor;

    @Column(name = "num_cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "fl_ativo")
    private Boolean ativo;

    public static Fornecedor create(FornecedorDTO fornecedorDTO) {
        return new ModelMapper().map(fornecedorDTO, Fornecedor.class);
    }
}
