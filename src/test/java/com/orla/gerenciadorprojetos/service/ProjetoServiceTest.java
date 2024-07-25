package com.orla.gerenciadorprojetos.service;

import com.orla.gerenciadorprojetos.model.Funcionario;
import com.orla.gerenciadorprojetos.model.GestaoProjeto;
import com.orla.gerenciadorprojetos.model.FuncionarioDTO;
import com.orla.gerenciadorprojetos.model.Projeto;
import com.orla.gerenciadorprojetos.model.ProjetoDTO;
import com.orla.gerenciadorprojetos.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProjetoServiceTest {

    @InjectMocks
    private ProjetoService projetoService;

    @Mock
    private ProjetoRepository projetoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarProjeto() {
        Projeto projeto = new Projeto();
        projeto.setProjetoId(1L);
        projeto.setNome("Projeto A");
        projeto.setDataCriacao(LocalDate.now());

        when(projetoRepository.save(projeto)).thenReturn(projeto);

        ResponseEntity<Projeto> response = projetoService.criarProjeto(projeto);

        assertEquals(projeto, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testListaProjetosFuncionarios() {
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setFuncionarioId(1L);
        funcionario1.setNome("João Mendonça");
        funcionario1.setCpf("123.456.789-01");
        funcionario1.setEmail("joao@teste.com");
        funcionario1.setSalario(5000.00);

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setFuncionarioId(2L);
        funcionario2.setNome("Neto Silva");
        funcionario2.setCpf("987.654.321-00");
        funcionario2.setEmail("neto@teste.com");
        funcionario2.setSalario(6000.00);

        GestaoProjeto fp1 = new GestaoProjeto();
        fp1.setFuncionario(funcionario1);

        GestaoProjeto fp2 = new GestaoProjeto();
        fp2.setFuncionario(funcionario2);

        Projeto projeto = new Projeto();
        projeto.setProjetoId(1L);
        projeto.setNome("Projeto A");
        projeto.setDataCriacao(LocalDate.now());
        projeto.setFuncionarioProjetos(List.of(fp1, fp2));

        List<Projeto> projetos = List.of(projeto);

        when(projetoRepository.findAllWithFuncionarios()).thenReturn(projetos);

        List<ProjetoDTO> resultado = projetoService.listaProjetosFuncionarios();

        assertEquals(1, resultado.size());
        ProjetoDTO dto = resultado.get(0);
        assertEquals("Projeto A", dto.getNome());
        assertEquals(2, dto.getFuncionarios().size());

        FuncionarioDTO funcionarioDTO1 = dto.getFuncionarios().get(0);
        assertEquals(1L, funcionarioDTO1.getFuncionarioId());
        assertEquals("Funcionario A", funcionarioDTO1.getNome());

        FuncionarioDTO funcionarioDTO2 = dto.getFuncionarios().get(1);
        assertEquals(2L, funcionarioDTO2.getFuncionarioId());
        assertEquals("Funcionario B", funcionarioDTO2.getNome());
    }
}
