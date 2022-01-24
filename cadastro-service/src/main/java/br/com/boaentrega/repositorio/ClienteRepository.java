package br.com.boaentrega.repositorio;

import br.com.boaentrega.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Santos
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
