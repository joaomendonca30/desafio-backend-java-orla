package com.orla.gerenciadorprojetos.repository;

import com.orla.gerenciadorprojetos.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    @Query("SELECT p FROM Projeto p LEFT JOIN FETCH p.funcionarioProjetos fp LEFT JOIN FETCH fp.funcionario")
    List<Projeto> findAllWithFuncionarios();

}
