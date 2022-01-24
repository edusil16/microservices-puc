package br.com.boaentrega.repositorio;

import br.com.boaentrega.modelo.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Santos
 */
@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {
    
}
