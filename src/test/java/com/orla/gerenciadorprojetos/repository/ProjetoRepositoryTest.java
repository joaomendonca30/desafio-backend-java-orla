package com.orla.gerenciadorprojetos.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import com.orla.gerenciadorprojetos.model.Projeto;
import com.orla.gerenciadorprojetos.repository.ProjetoRepository;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ProjetoRepositoryTest {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Test
    @Transactional
    public void testFindAll() {
        Projeto projeto = new Projeto();
        projeto.setNome("Teste - Projeto Repository");
        projeto.setDataCriacao(LocalDate.now());
        projetoRepository.save(projeto);

        List<Projeto> projetos = projetoRepository.findAll();
        assertThat(projetos).isNotEmpty();
    }
}
