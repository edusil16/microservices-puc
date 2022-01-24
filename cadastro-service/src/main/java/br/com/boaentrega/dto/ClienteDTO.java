package br.com.boaentrega.dto;

import br.com.boaentrega.modelo.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Eduardo Santos
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    
    private String id;
    private String name;

    public static ClienteDTO create(Cliente cliente) {
        return new ModelMapper().map(cliente, ClienteDTO.class);
    }
}
