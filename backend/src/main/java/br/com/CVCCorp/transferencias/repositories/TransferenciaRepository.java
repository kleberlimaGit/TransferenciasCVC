package br.com.CVCCorp.transferencias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.CVCCorp.transferencias.entities.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

}
