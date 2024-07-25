package com.orla.gerenciadorprojetos.service;

import com.orla.gerenciadorprojetos.model.*;
import com.orla.gerenciadorprojetos.repository.FuncionarioRepository;
import com.orla.gerenciadorprojetos.repository.GestaoProjetoRepository;
import com.orla.gerenciadorprojetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GestaoProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private GestaoProjetoRepository gestaoProjetoRepository;

    public ResponseEntity<String> relacionarProjetoFuncionario( Relacionamento request) {

        for (Relacionamento.Gestao movimentacao : request.getGestao()) {

            Projeto projeto = projetoRepository.findById(movimentacao.getProjetoId())
                    .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado com o Id: " + movimentacao.getProjetoId()));

            Funcionario funcionario = funcionarioRepository.findByNome(movimentacao.getNomeFuncionario())
                    .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado com o nome: " + movimentacao.getNomeFuncionario()));

            GestaoProjeto funcionarioProjeto = new GestaoProjeto();
            funcionarioProjeto.setProjeto(projeto);
            funcionarioProjeto.setFuncionario(funcionario);

            gestaoProjetoRepository.save(funcionarioProjeto);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("O relacionamento foi criado com sucesso.");
    }
}
