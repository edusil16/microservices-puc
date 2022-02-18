/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.boaentrega.repository;

import br.com.boaentrega.model.Perfil;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Santos
 */
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    
    @Query(value = "select distinct * from tb_perfil p where p.descricao = :descricao" , nativeQuery = true)
    Perfil buscarPorDescricaoPerfil(String descricao);
    
}
