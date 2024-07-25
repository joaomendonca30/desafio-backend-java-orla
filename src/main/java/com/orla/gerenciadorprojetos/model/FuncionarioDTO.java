package com.orla.gerenciadorprojetos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {

    private Long funcionarioId;
    private String nome;
    private String cpf;
    private String email;
    private Double salario;

}
