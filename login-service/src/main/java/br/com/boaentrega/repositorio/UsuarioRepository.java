/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.boaentrega.repositorio;

import br.com.boaentrega.modelo.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Santos
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    @Query(value = "select * from tb_usuario u\n" +
            "inner join tb_usuario_perfil upf on upf.id_usuario = u.id_usuario \n" +
            "inner join tb_perfil pf on pf.id_perfil = upf.id_perfil \n" +
            "where u.user_login = :usuario", nativeQuery = true)
    Optional<Usuario> buscarPorLogin(String usuario);
}
