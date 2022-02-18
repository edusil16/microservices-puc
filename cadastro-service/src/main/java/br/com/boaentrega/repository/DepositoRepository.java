package br.com.boaentrega.repository;

import br.com.boaentrega.model.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Santos
 */
@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {
    
}
