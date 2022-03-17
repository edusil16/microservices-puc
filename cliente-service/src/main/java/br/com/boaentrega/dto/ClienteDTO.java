/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.dto;

import br.com.boaentrega.model.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    private String name;
    private String endereco;

    public static ClienteDTO create(Cliente cliente) {
        return new ModelMapper().map(cliente, ClienteDTO.class);
    }
}
