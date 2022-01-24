/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.modelo;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Eduardo Santos
 */
@Entity
@Table(name="tb_usuario")
@Data
public class Usuario {
 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id_usuario")
    private Long id;
    
    @Column(name="login")
    private String login;
    
    @Column(name="senha")
    private String senha;
    
    @OneToOne(mappedBy="usuario")
    @NotNull
    private Cliente usuarioCliente;
    
    @OneToOne(mappedBy="usuario")
    @NotNull
    private Fornecedor usuarioFornecedor;
}
