package br.com.boaentrega.repository;

import br.com.boaentrega.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo Santos
 */
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    
}
