package com.orla.gerenciadorprojetos.controller;

import com.orla.gerenciadorprojetos.model.Projeto;
import com.orla.gerenciadorprojetos.model.ProjetoDTO;

import com.orla.gerenciadorprojetos.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orla/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<Projeto> requestProjeto(@RequestBody Projeto projeto) {
        return projetoService.criarProjeto(projeto);
    }

    @GetMapping
    public List<ProjetoDTO> getListarProjetos() {
        return projetoService.listaProjetosFuncionarios();
    }
}
