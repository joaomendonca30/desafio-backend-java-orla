package com.orla.gerenciadorprojetos.repository;

import com.orla.gerenciadorprojetos.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByNome(String nome);
}
