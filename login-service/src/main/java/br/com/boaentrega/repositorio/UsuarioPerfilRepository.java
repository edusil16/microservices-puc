/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.boaentrega.repositorio;

import br.com.boaentrega.modelo.UsuarioPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Santos
 */
@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long>{
    
}
