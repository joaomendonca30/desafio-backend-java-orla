package com.orla.gerenciadorprojetos.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "gestão_projeto")
public class GestaoProjeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gestaoId;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
}
