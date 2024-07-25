package com.orla.gerenciadorprojetos.repository;

import com.orla.gerenciadorprojetos.model.GestaoProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GestaoProjetoRepository extends JpaRepository<GestaoProjeto, Long> {
}
