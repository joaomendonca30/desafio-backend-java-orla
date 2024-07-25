package com.orla.gerenciadorprojetos.service;


import com.orla.gerenciadorprojetos.model.Funcionario;
import com.orla.gerenciadorprojetos.model.FuncionarioDTO;
import com.orla.gerenciadorprojetos.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FuncionarioServiceTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarFuncionario_Success() {
        // Prepare test data
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(null, "João", "12345678900", "joao@teste.com", 3000.0);

        Funcionario funcionario = new Funcionario();
        funcionario.setFuncionarioId(1L);
        funcionario.setNome("João");
        funcionario.setCpf("12345678900");
        funcionario.setEmail("joao@teste.com");
        funcionario.setSalario(3000.0);

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        // Call the service method
        ResponseEntity<FuncionarioDTO> response = funcionarioService.criarFuncionario(funcionarioDTO);

        // Verify the results
        assertNotNull(response);
        assertEquals(ResponseEntity.ok(new FuncionarioDTO(1L, "João", "12345678900", "joao@teste.com", 3000.0)), response);
        verify(funcionarioRepository).save(any(Funcionario.class));
    }

    @Test
    public void testCriarFuncionario_EmptyDTO() {
        // Prepare test data
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(null, "", "", "", 0.0);

        Funcionario funcionario = new Funcionario();
        funcionario.setFuncionarioId(1L);
        funcionario.setNome("");
        funcionario.setCpf("");
        funcionario.setEmail("");
        funcionario.setSalario(0.0);

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        // Call the service method
        ResponseEntity<FuncionarioDTO> response = funcionarioService.criarFuncionario(funcionarioDTO);

        // Verify the results
        assertNotNull(response);
        assertEquals(ResponseEntity.ok(new FuncionarioDTO(1L, "", "", "", 0.0)), response);
        verify(funcionarioRepository).save(any(Funcionario.class));
    }
}
