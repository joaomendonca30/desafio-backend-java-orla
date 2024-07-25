package com.orla.gerenciadorprojetos.service;

import com.orla.gerenciadorprojetos.model.*;
import com.orla.gerenciadorprojetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository projetoRepository;

    public ResponseEntity<Projeto> criarProjeto( Projeto projeto) {
        Projeto savedProjeto = projetoRepository.save(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProjeto);
    }

    public List<ProjetoDTO> listaProjetosFuncionarios() {
        List<Projeto> projetos = projetoRepository.findAllWithFuncionarios();

        return projetos.stream().map(projeto -> {
            List<FuncionarioDTO> funcionarioDTOs = projeto.getFuncionarioProjetos().stream()
                    .map(fp -> new FuncionarioDTO(
                            fp.getFuncionario().getFuncionarioId(),
                            fp.getFuncionario().getNome(),
                            fp.getFuncionario().getCpf(),
                            fp.getFuncionario().getEmail(),
                            fp.getFuncionario().getSalario()
                    ))
                    .collect(Collectors.toList());

            return new ProjetoDTO(
                    projeto.getProjetoId(),
                    projeto.getNome(),
                    projeto.getDataCriacao(),
                    funcionarioDTOs
            );
        }).collect(Collectors.toList());
    }
}
