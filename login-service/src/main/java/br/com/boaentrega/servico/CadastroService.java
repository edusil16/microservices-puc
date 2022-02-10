/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.servico;

import br.com.boaentrega.dto.UsuarioDTO;
import br.com.boaentrega.modelo.Usuario;
import br.com.boaentrega.modelo.UsuarioPerfil;
import br.com.boaentrega.repositorio.PerfilRepository;
import br.com.boaentrega.repositorio.UsuarioPerfilRepository;
import br.com.boaentrega.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo Santos
 */
@Service
public class CadastroService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;

    public void cadastrarUsuario(UsuarioDTO usuario) {
        var perfil = perfilRepository.buscarPorDescricaoPerfil(usuario.getPerfil());

        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String senhaCriptografada = bcryptPasswordEncoder.encode(usuario.getSenha());
        
        Usuario usuarioNovo = new Usuario();
        usuarioNovo.setLogin(usuario.getLogin());
        usuarioNovo.setSenha(senhaCriptografada);
        usuarioNovo.setSituacao(Boolean.TRUE);

        var usuarioCriado = usuarioRepository.save(usuarioNovo);
        
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
        usuarioPerfil.setIdPerfil(perfil.getId());
        usuarioPerfil.setIdUsuario(usuarioCriado.getIdUsuario());
        
        usuarioPerfilRepository.save(usuarioPerfil);
    }
    
    public Boolean validarUsuarioExistente(String login) {
        /* Método para validar se o Usuario já existe na base */
        
        return false;
    }
}
