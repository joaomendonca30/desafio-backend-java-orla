package com.orla.gerenciadorprojetos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "projeto")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Projeto {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projetoId;

    private String nome;
    private LocalDate dataCriacao;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GestaoProjeto> funcionarioProjetos;
}