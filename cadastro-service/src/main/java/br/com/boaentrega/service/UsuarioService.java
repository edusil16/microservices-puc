/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.service;

import br.com.boaentrega.dto.UsuarioDTO;
import br.com.boaentrega.message.UsuarioSendMessage;
import br.com.boaentrega.model.dominio.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo Santos
 */
@Service
public class UsuarioService {

    private final UsuarioSendMessage usuarioSendMessage;

    @Autowired
    public UsuarioService(UsuarioSendMessage usuarioSendMessage) {
        this.usuarioSendMessage = usuarioSendMessage;
    }

    public void publicarUsuario(UsuarioDTO usuario) {
        Perfil perfil = Perfil.obterPerfilPorValor(usuario.getPerfil());
        usuario.setPerfil(perfil.toString());
        usuarioSendMessage.sendMessageCliente(usuario);
    }
}
