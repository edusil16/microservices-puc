package br.com.boaentrega.repositorio;

import br.com.boaentrega.modelo.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Santos
 */
@Repository
public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long> {
    
}
