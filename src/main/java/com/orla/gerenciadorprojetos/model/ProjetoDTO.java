package com.orla.gerenciadorprojetos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDTO {
    private Long projetoId;
    private String nome;
    private LocalDate dataCriacao;
    private List<FuncionarioDTO> funcionarios;


}