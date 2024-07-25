package com.orla.gerenciadorprojetos.service;

import com.orla.gerenciadorprojetos.model.Funcionario;
import com.orla.gerenciadorprojetos.model.GestaoProjeto;
import com.orla.gerenciadorprojetos.model.Projeto;
import com.orla.gerenciadorprojetos.model.Relacionamento;
import com.orla.gerenciadorprojetos.repository.FuncionarioRepository;
import com.orla.gerenciadorprojetos.repository.GestaoProjetoRepository;
import com.orla.gerenciadorprojetos.repository.ProjetoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GestaoProjetoServiceTest {

    @InjectMocks
    private GestaoProjetoService gestaoProjetoService;

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private GestaoProjetoRepository gestaoProjetoRepository;

    public GestaoProjetoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRelacionarProjetoFuncionario_Success() {
        // Prepare test data
        Projeto projeto = new Projeto();
        projeto.setProjetoId(1L);
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");

        Relacionamento.Gestao movimentacao = new Relacionamento.Gestao();
        movimentacao.setProjetoId(1L);
        movimentacao.setNomeFuncionario("João");

        Relacionamento relacionamento = new Relacionamento();
        relacionamento.setMovimentacao(Collections.singletonList(movimentacao));

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(funcionarioRepository.findByNome("João")).thenReturn(Optional.of(funcionario));
        when(gestaoProjetoRepository.save(any(GestaoProjeto.class))).thenReturn(new GestaoProjeto());

        // Call the service method
        ResponseEntity<String> response = gestaoProjetoService.relacionarProjetoFuncionario(relacionamento);

        // Verify the results
        assertEquals(ResponseEntity.ok("O relacionamento foi criado com sucesso."), response);
        verify(projetoRepository).findById(1L);
        verify(funcionarioRepository).findByNome("João");
        verify(gestaoProjetoRepository).save(any(GestaoProjeto.class));
    }

    @Test
    public void testRelacionarProjetoFuncionario_ProjetoNotFound() {
        // Prepare test data
        Relacionamento.Gestao movimentacao = new Relacionamento.Gestao();
        movimentacao.setProjetoId(1L);
        movimentacao.setNomeFuncionario("João");

        Relacionamento relacionamento = new Relacionamento();
        relacionamento.setMovimentacao(Collections.singletonList(movimentacao));

        when(projetoRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method and verify exception
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            gestaoProjetoService.relacionarProjetoFuncionario(relacionamento);
        });

        assertEquals("Projeto não encontrado com o Id: 1", thrown.getMessage());
        verify(projetoRepository).findById(1L);
        verify(funcionarioRepository, never()).findByNome(anyString());
        verify(gestaoProjetoRepository, never()).save(any(GestaoProjeto.class));
    }

    @Test
    public void testRelacionarProjetoFuncionario_FuncionarioNotFound() {
        // Prepare test data
        Projeto projeto = new Projeto();
        projeto.setProjetoId(1L);

        Relacionamento.Gestao movimentacao = new Relacionamento.Gestao();
        movimentacao.setProjetoId(1L);
        movimentacao.setNomeFuncionario("João");

        Relacionamento relacionamento = new Relacionamento();
        relacionamento.setMovimentacao(Collections.singletonList(movimentacao));

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(funcionarioRepository.findByNome("João")).thenReturn(Optional.empty());

        // Call the service method and verify exception
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            gestaoProjetoService.relacionarProjetoFuncionario(relacionamento);
        });

        assertEquals("Funcionário não encontrado com o nome: João", thrown.getMessage());
        verify(projetoRepository).findById(1L);
        verify(funcionarioRepository).findByNome("João");
        verify(gestaoProjetoRepository, never()).save(any(GestaoProjeto.class));
    }
}
