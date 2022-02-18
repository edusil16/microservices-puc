package br.com.boaentrega.repository;

import br.com.boaentrega.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Santos
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
