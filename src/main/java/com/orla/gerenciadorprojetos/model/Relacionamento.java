package com.orla.gerenciadorprojetos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relacionamento {

    @JsonProperty("gestao")
    private List<Gestao> gestao;

    public List<Gestao> getGestao() {
        return gestao;
    }

    public void setGestao(List<Gestao> gestao) {
        this.gestao = gestao;
    }

    public static class Gestao {

        @JsonProperty("projetoId")
        private Long projetoId;

        @JsonProperty("nomeFuncionario")
        private String nomeFuncionario;

        public Long getProjetoId() {
            return projetoId;
        }

        public void setProjetoId(Long projetoId) {
            this.projetoId = projetoId;
        }

        public String getNomeFuncionario() {
            return nomeFuncionario;
        }

        public void setNomeFuncionario(String nomeFuncionario) {
            this.nomeFuncionario = nomeFuncionario;
        }
    }
}
