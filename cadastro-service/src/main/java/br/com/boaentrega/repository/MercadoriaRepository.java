package br.com.boaentrega.repository;

import br.com.boaentrega.model.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Santos
 */
@Repository
public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long> {
    
}
