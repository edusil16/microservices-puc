/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.jwt.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Eduardo Santos
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class JwtRequest 
        implements Serializable {
    
    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;
}
