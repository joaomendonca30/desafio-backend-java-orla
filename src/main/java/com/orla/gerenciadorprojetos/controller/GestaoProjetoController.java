package com.orla.gerenciadorprojetos.controller;

import com.orla.gerenciadorprojetos.model.*;
import com.orla.gerenciadorprojetos.service.GestaoProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class GestaoProjetoController {
    @Autowired
    private GestaoProjetoService gestaoProjetoService;

    @PostMapping("orla/api/gestao-projeto")
    public ResponseEntity<String> relacionarProjetoFuncionario(@RequestBody Relacionamento request) {
        return gestaoProjetoService.relacionarProjetoFuncionario(request);
    }
}
