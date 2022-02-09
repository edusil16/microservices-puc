/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.message;

import br.com.boaentrega.dto.UsuarioDTO;
import br.com.boaentrega.modelo.Usuario;
import br.com.boaentrega.repositorio.UsuarioRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 * @author Eduardo Santos
 */
@Component
public class UsuarioReceiveMessage {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioReceiveMessage(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @RabbitListener(queues = {"${cadastro.usuario.rabbitmq.queue}"})
    public void receiveMessageUsuario(@Payload UsuarioDTO usuario) {
        System.out.println(usuario);
        usuarioRepository.save(Usuario.create(usuario));
    }
}
