package com.orla.gerenciadorprojetos.repository;

import com.orla.gerenciadorprojetos.model.Funcionario;
import com.orla.gerenciadorprojetos.model.Projeto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Test
    @Transactional
    public void testFindAll() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Luis Mendonça");
        funcionario.setCpf("123.456.789-00");
        funcionario.setEmail("luis.mendonca@teste.com");
        funcionario.setSalario(3000.00);
        funcionarioRepository.save(funcionario);

        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        assertThat(funcionarios).isNotEmpty();
        assertThat(funcionarios.get(0).getNome()).isEqualTo("Luis Mendonça");
    }
}
