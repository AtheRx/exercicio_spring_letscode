package br.com.bb.letscode.exercicio1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bb.letscode.exercicio1.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
