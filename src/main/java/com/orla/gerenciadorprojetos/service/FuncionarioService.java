package com.orla.gerenciadorprojetos.service;

import com.orla.gerenciadorprojetos.model.Funcionario;
import com.orla.gerenciadorprojetos.model.FuncionarioDTO;
import com.orla.gerenciadorprojetos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public ResponseEntity<FuncionarioDTO> criarFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCpf(funcionarioDTO.getCpf());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setSalario(funcionarioDTO.getSalario());
        Funcionario savedFuncionario = funcionarioRepository.save(funcionario);

        FuncionarioDTO savedFuncionarioDTO = new FuncionarioDTO(
                savedFuncionario.getFuncionarioId(),
                savedFuncionario.getNome(),
                savedFuncionario.getCpf(),
                savedFuncionario.getEmail(),
                savedFuncionario.getSalario()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFuncionarioDTO);
    }
}