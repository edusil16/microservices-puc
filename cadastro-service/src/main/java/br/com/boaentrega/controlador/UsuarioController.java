/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.controlador;

import br.com.boaentrega.dto.UsuarioDTO;
import br.com.boaentrega.servico.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Eduardo Santos
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody UsuarioDTO usuario) {
        try {
            usuarioService.publicarUsuario(usuario);
            return ResponseEntity.ok("Cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    
}
