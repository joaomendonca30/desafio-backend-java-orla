package com.orla.gerenciadorprojetos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "funcionario")
public class Funcionario {


    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long funcionarioId;

    private String nome;
    private String cpf;
    private String email;
    private Double salario;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GestaoProjeto> funcionarioProjetos;
}
