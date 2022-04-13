/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.boaentrega.repository;

import br.com.boaentrega.model.Reembolso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Santos
 */
@Repository
public interface ReembolsoRepository extends JpaRepository<Reembolso, Long> {

    @Query(value = "select * from tb_reembolso r where r.vl_reembolso is null", nativeQuery = true)
    List<Reembolso> listaReembolsoAPagar();
    
    @Query(value = "select * from tb_reembolso r where r.vl_reembolso is not null", nativeQuery = true)
    List<Reembolso> listaReembolsoPagas();

}
