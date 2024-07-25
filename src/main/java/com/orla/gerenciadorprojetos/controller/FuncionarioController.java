package com.orla.gerenciadorprojetos.controller;


import com.orla.gerenciadorprojetos.model.FuncionarioDTO;

import com.orla.gerenciadorprojetos.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orla/api/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncionarioDTO> createFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        return funcionarioService.criarFuncionario(funcionarioDTO);
    }

}

