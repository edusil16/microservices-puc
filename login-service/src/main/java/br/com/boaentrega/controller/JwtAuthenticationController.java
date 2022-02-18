/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.controller;

import br.com.boaentrega.dto.JwtRequestDTO;
import br.com.boaentrega.dto.JwtResponseDTO;
import br.com.boaentrega.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Eduardo Santos
 */
@CrossOrigin
@RestController
public class JwtAuthenticationController {

    private final JwtService jwtService;

    @Autowired
    public JwtAuthenticationController (JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping({"/login"})
    public JwtResponseDTO createJwtToken(@RequestBody JwtRequestDTO jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

}
